package cli.commands

import cli.environments.Env
import cli.outputs.ConsoleOutput
import cli.outputs.Output
import java.io.File

class ExternalProcessCommand(
    private val command: String,
    override val environment: Env
) : Command() {
    override fun run(input: String): Output {
        val processBuilder = ProcessBuilder("sh", "-c", *command.split(" ").toTypedArray())
            .directory(File(environment.location).absoluteFile)
            .redirectErrorStream(true)
        val process = processBuilder.start()
            .also { it.waitFor() }
        return ConsoleOutput(process.inputStream.readAllBytes().toString(Charsets.UTF_8))
    }
}
