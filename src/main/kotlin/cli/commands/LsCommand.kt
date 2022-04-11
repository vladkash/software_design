package cli.commands

import cli.environments.MutableEnv
import cli.outputs.ConsoleOutput
import cli.outputs.Output
import java.io.File

class LsCommand(
    arg: String,
    environment: MutableEnv
) : CommandWithArgs(arg, environment) {
    override val allowedFlags: Set<CommandFlag>
        get() = emptySet()

    override fun run(input: String): Output {
        val directory = when {
            arg.isEmpty() -> File(environment.location)
            else -> File(arg)
        }
        val dirContent = directory.listFiles()
            ?.joinToString(" ") { it.name }
            ?: ""
        return ConsoleOutput(dirContent)
    }
}
