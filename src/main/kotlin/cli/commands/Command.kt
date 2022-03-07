package cli.commands

import cli.environments.Env
import cli.environments.MutableEnv
import cli.outputs.ConsoleOutput
import cli.outputs.Output

interface Command {
    fun run(input: String, env: Env): Output {
        return ConsoleOutput("")
    }

    fun modifyEnv(env: MutableEnv) {}
}
