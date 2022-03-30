package cli.commands

import cli.TestWithFakerAndEnv
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.io.File
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.assertEquals

internal class CatTestWithFakerAndEnv : TestWithFakerAndEnv() {

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
        val command = CatCommand("")
        val input = faker.random.randomString()
        val res = command.run(input, env).forNextCommand()
        assertEquals(input, res)
    }

    @Test
    fun runWithArgStrongQuoting() {
        val command = CatCommand(filePath)
        val res = command.run("", env).forNextCommand()
        assertEquals(fileContent, res)
    }

    @Test
    fun runWithArgWeakQuoting() {
        env.put(envKey, filePath.substring(filePath.length / 2))
        val command = CatCommand("${filePath.substring(0, filePath.length / 2)}$$envKey")
        val res = command.run("", env).forNextCommand()
        assertEquals(fileContent, res)
    }

    @Test
    fun runWithIncorrectArg() {
        val command = CatCommand(faker.random.randomString())
        assertThrows<CommandRunningException> { command.run("", env).forNextCommand() }
    }
}
