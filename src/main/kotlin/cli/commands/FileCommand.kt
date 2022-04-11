package cli.commands

import cli.environments.MutableEnv
import java.io.File

abstract class FileCommand(
    arg: String,
    environment: MutableEnv
) : CommandWithArgs(arg, environment) {
    protected fun readFile(path: String): String {
        val file = File(path)
        if (!file.exists()) {
            throw CommandRunningException("File $path doesn't exist")
        }
        return file.readText()
    }
}
