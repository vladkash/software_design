package cli.commands

import cli.TestWithFakerAndEnv
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class AssignEnvCommandTest : TestWithFakerAndEnv() {

    @Test
    fun modifyEnv() {
        val key = faker.random.randomString()
        val value = faker.random.randomString()
        val command = AssignEnvCommand(key, value)
        command.modifyEnv(env)
        assert(env.contains(key))
        assertEquals(value, env[key])
    }
}
