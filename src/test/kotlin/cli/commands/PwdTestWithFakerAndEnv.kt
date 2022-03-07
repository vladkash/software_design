package cli.commands

import cli.TestWithFakerAndEnv
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File

internal class PwdTestWithFakerAndEnv : TestWithFakerAndEnv() {

    @Test
    fun run() {
        val res = PwdCommand().run("", env)
        assertEquals(File("").absolutePath, res.forNextCommand())
    }
}
