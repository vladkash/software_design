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
        // удаляем лишний последний перенос строки
        content = content.substring(0, content.length - 2)
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
    fun runWithTwoAfter() {
        val substring = content.lines().first()
        val command = GrepCommand("-A 2 $substring")
        val res = command.run(content, env).forNextCommand()
        assertEquals(3, res.lines().size)
    }

    @Test
    fun runIgnoreCase() {
        val command = GrepCommand("-i TesT")
        val res = command.run(content, env).forNextCommand()
        assertEquals(content.lines().size, res.lines().size)
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
