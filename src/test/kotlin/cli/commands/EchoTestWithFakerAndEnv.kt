package cli.commands

import cli.TestWithFakerAndEnv
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class EchoTestWithFakerAndEnv : TestWithFakerAndEnv() {

    @Test
    fun runWithStrongQuoting() {
        val content = faker.random.randomString()
        val command = EchoCommand(content, env)
        val res = command.run("").forNextCommand()
        assertEquals(content, res)
    }

    @Test
    fun runWithStrongQuotingAndEnv() {
        val content = "${ faker.random.randomString() }$$envKey${ faker.random.randomString() }"
        val command = EchoCommand("'$content'", env)
        val res = command.run("").forNextCommand()
        assertEquals(content, res)
    }

    @Test
    fun runWithWeakQuotingAndEnv() {
        val content = "${ faker.random.randomString() }$$envKey${ faker.random.randomString() }"
        val command = EchoCommand(content, env)
        val res = command.run("").forNextCommand()
        assertEquals(env.replaceVars(content), res)
    }
}
