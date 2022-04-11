package cli.commands

import cli.TestWithFakerAndEnv
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.nio.file.Files

internal class CdTestWithFakerAndEnv : TestWithFakerAndEnv() {
    @Test
    fun testCdWithNoArguments() {
        val cd = CdCommand("", env)

        val actual = cd.run("").forNextCommand()
        assertEquals("", actual)

        val userHome = System.getProperty("user.home")
        val pwd = PwdCommand(env)
        assertEquals(userHome, pwd.run("").forNextCommand())
    }

    @Test
    fun testCdWithArgument() = withTempDir { dirName ->
        val cd = CdCommand(dirName, env)

        val actual = cd.run("").forNextCommand()
        assertEquals("", actual)

        val pwd = PwdCommand(env)
        assertEquals(dirName, pwd.run("").forNextCommand())
    }

    private fun withTempDir(block: (String) -> Unit) {
        val file = Files.createTempDirectory("dirForCdTest").toFile()
        file.deleteOnExit()
        block(file.name)
    }
}
