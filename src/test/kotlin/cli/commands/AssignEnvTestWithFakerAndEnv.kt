package cli.commands

import cli.TestWithFakerAndEnv
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

internal class AssignEnvTestWithFakerAndEnv : TestWithFakerAndEnv() {

    @Test
    fun modifyEnv() {
        val key = faker.random.randomString()
        val value = faker.random.randomString()
        val command = AssignEnvCommand(key, value)

        command.modifyEnv(env)
        assertTrue(env.contains(key))
        assertEquals(value, env[key])
    }
}
