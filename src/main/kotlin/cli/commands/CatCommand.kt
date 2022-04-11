package cli.commands

import cli.environments.MutableEnv
import cli.outputs.ConsoleOutput
import cli.outputs.Output

open class CatCommand(
    arg: String,
    environment: MutableEnv
) : FileCommand(arg, environment) {

    override val allowedFlags: Set<CommandFlag>
        get() = emptySet()

    override fun run(input: String): Output =
        when {
            arg.isEmpty() -> ConsoleOutput(input)
            else -> ConsoleOutput(readFile(arg))
        }
}
