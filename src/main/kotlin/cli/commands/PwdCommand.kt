package cli.commands

import cli.environments.Env
import cli.outputs.ConsoleOutput
import cli.outputs.Output

class PwdCommand : Command {
    override fun run(input: String, env: Env): Output {
        return ConsoleOutput(System.getProperty("user.dir"))
    }
}
