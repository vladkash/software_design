package cli.commands

import cli.environments.Env
import cli.outputs.ConsoleOutput
import cli.outputs.Output

class EchoCommand(arg: String) :
    CommandWithArgs(arg) {
    override val allowedFlags: Set<CommandFlag>
        get() = emptySet()

    override fun run(input: String, env: Env): Output {
        return ConsoleOutput(
            if (weakQuoting) {
                env.replaceVars(arg)
            } else {
                arg
            }
        )
    }
}
