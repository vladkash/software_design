package cli.commands

import cli.TestWithFakerAndEnv
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class PwdTestWithFakerAndEnv : TestWithFakerAndEnv() {

    @Test
    fun run() {
        val pwd = faker.random.randomString()
        env.put("PWD", pwd)
        val res = PwdCommand().run("", env)
        assertEquals(pwd, res.forNextCommand())
    }
}
