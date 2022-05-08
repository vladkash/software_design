package cli.commands

import cli.TestWithFakerAndEnv
import com.github.stefanbirkner.systemlambda.SystemLambda
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class ExitTestWithFakerAndEnv : TestWithFakerAndEnv() {

    @Test
    fun run() {
        val statusCode = SystemLambda.catchSystemExit { ExitCommand().run("", env) }
        assertEquals(0, statusCode)
    }
}
