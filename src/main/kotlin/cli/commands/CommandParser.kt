package cli.commands

import cli.environments.MutableEnv

class CommandParser {
    /**
     * Метод парсинга команды из строки.
     * Сначала пробует найти присвоение переменной,
     * после смотрит на первое слово в команде и на основании
     * него подставляет нужную команду
     * @param raw строка, в которой нужно найти команду
     */
    fun parse(raw: String): (MutableEnv) -> Command {

        val assignmentRegexRes = Regex("(\\w+)\\s*=\\s*(\\w+)").matchEntire(raw)

        if (assignmentRegexRes?.groupValues != null) {
            return { env ->
                AssignEnvCommand(
                    assignmentRegexRes.groupValues[1],
                    assignmentRegexRes.groupValues[2],
                    env
                )
            }
        }

        val strCommand = raw.trim().takeWhile { it != ' ' }

        val arg = raw.replace(strCommand, "").trim()

        when (strCommand) {
            "exit" -> return ::ExitCommand
            "pwd" -> return ::PwdCommand
        }

        val buildCommand = when (strCommand) {
            "cat" -> ::CatCommand
            "echo" -> ::EchoCommand
            "grep" -> ::GrepCommand
            "wc" -> ::WcCommand
            "ls" -> ::LsCommand
            "cd" -> ::CdCommand
            else -> return { env -> ExternalProcessCommand(raw, env) }
        }

        return { env -> buildCommand(arg, env) }
    }
}
