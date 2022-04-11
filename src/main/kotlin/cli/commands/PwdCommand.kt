package cli.commands

import cli.environments.Env
import cli.outputs.ConsoleOutput
import cli.outputs.Output

class PwdCommand(override val environment: Env) : Command() {
    override fun run(input: String): Output {
        return ConsoleOutput(environment.location)
    }
}
