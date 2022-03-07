package cli.commands

import cli.environments.Env
import cli.outputs.ConsoleOutput
import cli.outputs.Output

class ExternalProcessCommand(private val command: String) : Command {
    override fun run(input: String, env: Env): Output {
        val process = Runtime.getRuntime().exec("sh -c $command")
        return ConsoleOutput(process.inputStream.readAllBytes().toString(Charsets.UTF_8))
    }
}
