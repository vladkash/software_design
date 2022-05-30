package cli.commands

import cli.environments.MutableEnv
import cli.outputs.ConsoleOutput
import cli.outputs.Output

class CdCommand(
    arg: String,
    environment: MutableEnv
) : CommandWithArgs(arg, environment) {

    override val allowedFlags: Set<CommandFlag>
        get() = emptySet()

    override fun run(input: String): Output {
        val newLocation = when {
            arg.isEmpty() -> System.getProperty("user.home")
            else -> arg
        }
        environment.location = newLocation
        return ConsoleOutput("")
    }
}
