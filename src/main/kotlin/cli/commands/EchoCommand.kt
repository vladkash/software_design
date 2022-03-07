package cli.commands

import cli.environments.Env
import cli.outputs.ConsoleOutput
import cli.outputs.Output

class EchoCommand(private val arg: String, private val weakQuoting: Boolean) : Command {
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
