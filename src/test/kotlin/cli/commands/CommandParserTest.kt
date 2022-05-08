package cli.commands

import cli.TestWithFakerAndEnv
import org.junit.jupiter.api.Assertions.assertInstanceOf
import org.junit.jupiter.api.Test

internal class CommandParserTest : TestWithFakerAndEnv() {

    private val parser = CommandParser()

    @Test
    fun parseAssignment() {
        val res = parser.parse("foo = bar")
        assertInstanceOf(AssignEnvCommand::class.java, res)
    }

    @Test
    fun parseCat() {
        val res = parser.parse("cat 'test'")
        assertInstanceOf(CatCommand::class.java, res)
    }

    @Test
    fun parseEcho() {
        val res = parser.parse("echo \"test\"")
        assertInstanceOf(EchoCommand::class.java, res)
    }

    @Test
    fun parseExit() {
        val res = parser.parse("   exit    ")
        assertInstanceOf(ExitCommand::class.java, res)
    }

    @Test
    fun parsePwd() {
        val res = parser.parse("   pwd")
        assertInstanceOf(PwdCommand::class.java, res)
    }

    @Test
    fun parseWc() {
        val res = parser.parse("wc      file.txt")
        assertInstanceOf(WcCommand::class.java, res)
    }

    @Test
    fun parseExternalProcess() {
        val res = parser.parse("ls")
        assertInstanceOf(ExternalProcessCommand::class.java, res)
    }
}
