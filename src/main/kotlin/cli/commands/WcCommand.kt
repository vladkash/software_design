package cli.commands

import cli.environments.Env
import cli.outputs.ConsoleOutput
import cli.outputs.Output

class WcCommand(private val arg: String, private val weakQuoting: Boolean) : FileCommand() {
    override fun run(input: String, env: Env): Output {
        val content = if (arg.isEmpty()) input else readFile(if (weakQuoting) env.replaceVars(arg) else arg)
        return ConsoleOutput(getCounts(content))
    }

    private fun getCounts(string: String): String {
        return "${string.lines().filter { it.isNotEmpty() }.size}       ${string.trim().split(Regex("\\s+")).size}       ${string.length}       "
    }
}
