package cli.commands

import cli.environments.MutableEnv

class AssignEnvCommand(
    private val key: String,
    private val value: String,
    override val environment: MutableEnv
) : Command() {
    override fun modifyEnv() {
        environment.put(key, value)
    }
}
