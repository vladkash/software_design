package cli.commands

import cli.TestWithFakerAndEnv
import org.junit.jupiter.api.Assertions.assertInstanceOf
import org.junit.jupiter.api.Test

internal class CommandParserTest : TestWithFakerAndEnv() {

    private val parser = CommandParser()

    @Test
    fun parseAssignment() {
        val res = parseCommand("foo = bar")
        assertInstanceOf(AssignEnvCommand::class.java, res)
    }

    @Test
    fun parseCat() {
        val res = parseCommand("cat 'test'")
        assertInstanceOf(CatCommand::class.java, res)
    }

    @Test
    fun parseEcho() {
        val res = parseCommand("echo \"test\"")
        assertInstanceOf(EchoCommand::class.java, res)
    }

    @Test
    fun parseExit() {
        val res = parseCommand("   exit    ")
        assertInstanceOf(ExitCommand::class.java, res)
    }

    @Test
    fun parsePwd() {
        val res = parseCommand("   pwd")
        assertInstanceOf(PwdCommand::class.java, res)
    }

    @Test
    fun parseWc() {
        val res = parseCommand("wc      file.txt")
        assertInstanceOf(WcCommand::class.java, res)
    }

    @Test
    fun parseLs() {
        val res = parseCommand("ls")
        assertInstanceOf(LsCommand::class.java, res)
    }

    @Test
    fun parseCd() {
        val res = parseCommand("cd")
        assertInstanceOf(CdCommand::class.java, res)
    }

    @Test
    fun parseExternalProcess() {
        val res = parseCommand("ps")
        assertInstanceOf(ExternalProcessCommand::class.java, res)
    }

    private fun parseCommand(input: String): Command {
        val buildCommand = parser.parse(input)
        return buildCommand(env)
    }
}
