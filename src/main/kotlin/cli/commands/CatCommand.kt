package cli.commands

import cli.environments.Env
import cli.outputs.ConsoleOutput
import cli.outputs.Output

open class CatCommand(arg: String) :
    FileCommand(arg) {

    override val allowedFlags: Set<CommandFlag>
        get() = emptySet()

    override fun run(input: String, env: Env): Output {
        return ConsoleOutput(
            if (arg.isEmpty()) input else readFile(if (weakQuoting) env.replaceVars(arg) else arg)
        )
    }
}
