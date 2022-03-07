package cli.commands

class CommandParser {

    fun parse(raw: String): Command {

        val assignmentRegexRes = Regex("(\\w+)\\s*=\\s*(\\w+)").matchEntire(raw)

        if (assignmentRegexRes?.groupValues != null) {
            return AssignEnvCommand(assignmentRegexRes.groupValues[1], assignmentRegexRes.groupValues[2])
        }

        val strCommand = raw.trim().takeWhile { it != ' ' }
        var arg = raw.replace(strCommand, "").trim()
        var weakQuoting = false

        if (arg.length >= 2) {
            val first = arg.first()
            val last = arg.last()

            if (
                (first == '\'' && last == '\'') ||
                (first == '"' && last == '"')
            ) {
                arg = arg.drop(1).dropLast(1)
                if (first == '"') {
                    weakQuoting = true
                }
            } else {
                weakQuoting = true
            }
        }

        return when (strCommand) {
            "cat" -> CatCommand(arg, weakQuoting)
            "echo" -> EchoCommand(arg, weakQuoting)
            "exit" -> ExitCommand()
            "pwd" -> PwdCommand()
            "wc" -> WcCommand(arg, weakQuoting)
            else -> ExternalProcessCommand(raw)
        }
    }
}
