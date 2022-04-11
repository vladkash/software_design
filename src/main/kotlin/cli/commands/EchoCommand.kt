package cli.commands

import cli.environments.MutableEnv
import cli.outputs.ConsoleOutput
import cli.outputs.Output

class EchoCommand(
    arg: String,
    environment: MutableEnv
) : CommandWithArgs(arg, environment) {
    override val allowedFlags: Set<CommandFlag>
        get() = emptySet()

    override fun run(input: String): Output =
        ConsoleOutput(arg)
}
