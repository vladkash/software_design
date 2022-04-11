package cli.commands

import cli.environments.Env
import cli.outputs.Output
import kotlin.system.exitProcess

class ExitCommand(override val environment: Env) : Command() {
    override fun run(input: String): Output {
        exitProcess(0)
    }
}
