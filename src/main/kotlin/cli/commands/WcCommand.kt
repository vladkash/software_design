package cli.commands

import cli.environments.MutableEnv
import cli.outputs.ConsoleOutput
import cli.outputs.Output

class WcCommand(
    arg: String,
    environment: MutableEnv
) : FileCommand(arg, environment) {

    override val allowedFlags: Set<CommandFlag>
        get() = emptySet()

    override fun run(input: String): Output {
        val content = if (arg.isEmpty()) input else readFile(arg)
        return ConsoleOutput(getCounts(content))
    }

    private fun getCounts(string: String): String {
        return "${string.lines().filter { it.isNotEmpty() }.size}       ${string.trim().split(Regex("\\s+")).size}       ${string.length}       "
    }
}
