package cli.commands

import cli.TestWithFakerAndEnv
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class PwdTestWithFakerAndEnv : TestWithFakerAndEnv() {

    @Test
    fun run() {
        val pwd = faker.random.randomString()
        env.location = pwd
        val res = PwdCommand(env).run("")
        assertEquals(pwd, res.forNextCommand())
    }
}
