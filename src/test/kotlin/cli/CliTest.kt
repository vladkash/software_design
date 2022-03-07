package cli

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

internal class CliTest : TestWithFakerAndEnv() {

    private val cli = Cli(env)

    @Test
    fun execute() {
        assertDoesNotThrow {
            cli.execute("echo test | wc | wc | wc")
            cli.execute("echo $$envKey | wc | wc | wc")
            cli.execute("pwd | cat | wc")
            cli.execute("pwd | cat | wc")
            cli.execute("echo test1; echo test 2")
            cli.execute("foo = bar")
            cli.execute("cat foo")
        }
    }
}
