package cli.environments

import cli.TestWithFakerAndEnv
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class MutableEnvTest : TestWithFakerAndEnv() {

    @Test
    fun testEnvMethods() {
        val key = faker.random.randomString()
        val value = faker.random.randomString()
        env[key] = value
        assert(env.contains(key))
        assertEquals(value, env[key])
    }
}
