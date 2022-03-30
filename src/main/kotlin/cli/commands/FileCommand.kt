package cli.commands

import java.io.File

abstract class FileCommand(arg: String) :
    CommandWithArgs(arg) {
    protected fun readFile(path: String): String {
        val file = File(path)
        if (!file.exists()) {
            throw CommandRunningException("File $path doesn't exist")
        }
        return file.readText()
    }
}
