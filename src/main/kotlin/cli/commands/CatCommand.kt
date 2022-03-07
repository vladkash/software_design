package cli.commands

import cli.environments.Env
import cli.outputs.ConsoleOutput
import cli.outputs.Output

open class CatCommand(private val arg: String, private val weakQuoting: Boolean) : FileCommand() {
    override fun run(input: String, env: Env): Output {
        return ConsoleOutput(
            if (arg.isEmpty()) input else readFile(if (weakQuoting) env.replaceVars(arg) else arg)
        )
    }
}
