package cli.commands

import cli.TestWithFakerAndEnv
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class AssignEnvCommandTest : TestWithFakerAndEnv() {

    @Test
    fun modifyEnv() {
        val key = faker.random.randomString()
        val value = faker.random.randomString()
        val command = AssignEnvCommand(key, value, env)
        command.modifyEnv()
        assert(env.has(key))
        assertEquals(value, env.get(key))
    }
}
