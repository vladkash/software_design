package cli.commands

import cli.TestWithFakerAndEnv
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.io.File
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.assertEquals

internal class WcTestWithFakerAndEnv : TestWithFakerAndEnv() {

    private val fileContent = faker.random.randomString()
    private val filePath = "${faker.random.randomString(5)}.txt"

    @BeforeTest
    fun setUp() {
        File(filePath).writeText(fileContent)
    }

    @AfterTest
    fun setDown() {
        File(filePath).delete()
    }

    @Test
    fun runEmptyArg() {
        val command = WcCommand("", env)
        val input = faker.random.randomString()
        val res = command.run(input).forNextCommand()
        assertEquals(getCounts(input), res)
    }

    @Test
    fun runWithArgStrongQuoting() {
        val command = WcCommand(filePath, env)
        val res = command.run("").forNextCommand()
        assertEquals(getCounts(fileContent), res)
    }

    @Test
    fun runWithArgWeakQuoting() {
        env.put(envKey, filePath.substring(filePath.length / 2))
        val command = WcCommand("${filePath.substring(0, filePath.length / 2)}$$envKey", env)
        val res = command.run("").forNextCommand()
        assertEquals(getCounts(fileContent), res)
    }

    @Test
    fun runWithIncorrectArg() {
        val command = WcCommand(faker.random.randomString(), env)
        assertThrows<CommandRunningException> { command.run("").forNextCommand() }
    }

    private fun getCounts(string: String): String {
        return "${string.lines().filter { it.isNotEmpty() }.size}       ${string.trim().split(Regex("\\s+")).size}       ${string.length}       "
    }
}
