package cli.commands

class CommandParser {
    /**
     * Метод парсинга команды из строки.
     * Сначала пробует найти присвоение переменной,
     * после смотрит на первое слово в команде и на основании
     * него подставляет нужную команду
     * @param raw строка, в которой нужно найти команду
     */
    fun parse(raw: String): Command {

        val assignmentRegexRes = Regex("(\\w+)\\s*=\\s*(\\w+)").matchEntire(raw)

        if (assignmentRegexRes?.groupValues != null) {
            return AssignEnvCommand(assignmentRegexRes.groupValues[1], assignmentRegexRes.groupValues[2])
        }

        val strCommand = raw.trim().takeWhile { it != ' ' }

        val arg = raw.replace(strCommand, "").trim()

        return when (strCommand) {
            "cat" -> CatCommand(arg)
            "echo" -> EchoCommand(arg)
            "exit" -> ExitCommand()
            "pwd" -> PwdCommand()
            "wc" -> WcCommand(arg)
            else -> ExternalProcessCommand(raw)
        }
    }
}
