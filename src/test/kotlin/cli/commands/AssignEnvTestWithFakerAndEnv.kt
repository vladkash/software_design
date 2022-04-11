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
        val command = AssignEnvCommand(key, value, env)

        command.modifyEnv()
        assertTrue(env.has(key))
        assertEquals(value, env.get(key))
    }
}
