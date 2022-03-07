package cli.commands

import cli.environments.MutableEnv

class AssignEnvCommand(private val key: String, private val value: String) : Command {
    override fun modifyEnv(env: MutableEnv) {
        env.put(key, value)
    }
}
