package cli.commands

import cli.environments.Env
import cli.outputs.Output
import kotlin.system.exitProcess

class ExitCommand : Command {
    override fun run(input: String, env: Env): Output {
        exitProcess(0)
    }
}
