package cli.commands

import cli.TestWithFakerAndEnv
import org.junit.jupiter.api.Test
import kotlin.test.BeforeTest
import kotlin.test.assertEquals

internal class GrepTestWithFakerAndEnv : TestWithFakerAndEnv() {

    private var content = ""

    @BeforeTest
    fun setUp() {
        for (i in 1..10) {
            content += "${faker.random.randomString()} test ${faker.random.randomString()}\n"
            content += "${faker.random.randomString()}test${faker.random.randomString()}\n"
        }
    }

    @Test
    fun runEmptyArg() {
        val command = GrepCommand("")
        val res = command.run(content, env).forNextCommand()
        assertEquals(content.lines().size, res.lines().size)
    }

    @Test
    fun runFullWords() {
        val command = GrepCommand("-w test")
        val res = command.run(content, env).forNextCommand()
        assertEquals(content.lines().size / 2, res.lines().size)
    }

    @Test
    fun runWithTwoAfterFullWords() {
        val command = GrepCommand("-w -A 2 test")
        val res = command.run(content, env).forNextCommand()
        assertEquals(content.lines().size, res.lines().size)
    }

    @Test
    fun runWithTwoAfterFullWordsIgnoreCase() {
        val command = GrepCommand("-w -A 2 -i TesT")
        val res = command.run(content, env).forNextCommand()
        assertEquals(content.lines().size, res.lines().size)
    }
}
