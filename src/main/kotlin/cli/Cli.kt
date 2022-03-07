package cli

import cli.commands.CommandParser
import cli.commands.CommandRunningException
import cli.environments.MutableEnv
import cli.outputs.Output

class Cli(private val env: MutableEnv) {

    private val commandParser = CommandParser()

    fun execute(str: String) {

        str.split(';').forEach { pipeline ->

            val commands = pipeline.split('|').map { commandParser.parse(it.trim()) }

            try {
                var output: Output = commands.first().run("", env)

                if (commands.size == 1) {
                    commands.first().modifyEnv(env)
                }

                for (i in 1 until commands.size) {
                    output = commands[i].run(output.forNextCommand(), env)
                }

                output.sendOut()
            } catch (e: CommandRunningException) {
                println("Error while command running: ${e.message}")
            } catch (e: Exception) {
                println("Unhandled exception while command running: ${e.message}")
            }
        }
    }
}
