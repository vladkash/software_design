package cli.commands

import cli.environments.Env
import cli.environments.MutableEnv
import cli.environments.replaceVars
import cli.outputs.ConsoleOutput
import cli.outputs.Output
import kotlin.system.exitProcess

/**
 * Команда изменения переменной окружения
 */
class AssignEnvCommand(private val key: String, private val value: String) : Command {
    override fun modifyEnv(env: MutableEnv) {
        env[key] = value
    }
}

/**
 * Команда cat, выводит содержимае файла
 */
open class CatCommand(arg: String) :
    FileCommand(arg) {

    override val allowedFlags: Set<CommandFlag>
        get() = emptySet()

    override fun run(input: String, env: Env): Output {
        return ConsoleOutput(
            if (arg.isEmpty()) input else readFile(if (weakQuoting) env.replaceVars(arg) else arg)
        )
    }
}

/**
 * Команда echo, выодит переданный аргумент, игнорирует pipeline
 */
class EchoCommand(arg: String) :
    CommandWithArgs(arg) {
    override val allowedFlags: Set<CommandFlag>
        get() = emptySet()

    override fun run(input: String, env: Env): Output {
        return ConsoleOutput(
            if (weakQuoting) {
                env.replaceVars(arg)
            } else {
                arg
            }
        )
    }
}

/**
 * Команда exit - завершает процесс bash
 */
class ExitCommand : Command {
    override fun run(input: String, env: Env): Output {
        exitProcess(0)
    }
}

/**
 * Вызов внешнего процесса и чтение из его потока вывода
 */
class ExternalProcessCommand(private val command: String) : Command {
    override fun run(input: String, env: Env): Output {
        val process = Runtime.getRuntime().exec("sh -c $command")
        return ConsoleOutput(process.inputStream.readAllBytes().toString(Charsets.UTF_8))
    }
}

/**
 * Вывод текущей директории пользователя
 */
class PwdCommand : Command {
    override fun run(input: String, env: Env): Output {
        return ConsoleOutput(env["PWD"]!!)
    }
}

/**
 * Команда wc, вывод статистики по символам, словам и строкам
 */
class WcCommand(arg: String) : FileCommand(arg) {
    override val allowedFlags: Set<CommandFlag>
        get() = emptySet()

    override fun run(input: String, env: Env): Output {
        val content = if (arg.isEmpty()) input else readFile(if (weakQuoting) env.replaceVars(arg) else arg)
        return ConsoleOutput(getCounts(content))
    }

    private fun getCounts(string: String): String = "${string.lines().filter { it.isNotEmpty() }.size}       ${string.trim().split(Regex("\\s+")).size}       ${string.length}       "
}

/**
 * Команда grep, поиск по вхождениям
 */
class GrepCommand(arg: String) : FileCommand(arg) {
    companion object {
        const val IGNORE_CASE_FLAG = "i"
        const val FULL_WORDS_FLAG = "w"
        const val LINES_AFTER_MATCH_FLAG = "A"
    }

    override val allowedFlags: Set<CommandFlag>
        get() = setOf(
            CommandFlag(IGNORE_CASE_FLAG, false),
            CommandFlag(FULL_WORDS_FLAG, false),
            CommandFlag(LINES_AFTER_MATCH_FLAG, true)
        )

    override fun run(input: String, env: Env): Output {
        val options = mutableSetOf<RegexOption>()
        if (flags.containsKey(IGNORE_CASE_FLAG)) {
            options.add(RegexOption.IGNORE_CASE)
        }
        var pattern = if (weakQuoting) env.replaceVars(arg) else arg

        val lines = if (input.isNotEmpty()) {
            input.lines()
        } else {
            val fileName = pattern.split(' ').last()
            pattern = pattern.replace(fileName, "")
            readFile(fileName).lines()
        }

        if (flags.containsKey(FULL_WORDS_FLAG)) {
            pattern = "\\b($pattern)\\b"
        }
        val regex = Regex(pattern, options)

        val res = mutableListOf<String>()

        var linesAfter = flags[LINES_AFTER_MATCH_FLAG]?.toInt()
        if (linesAfter == null) {
            linesAfter = 0
        }
        var linesLeft = 0

        for (i in lines.indices) {
            if (regex.find(lines[i]) != null) {
                res.add(lines[i])
                linesLeft = Integer.max(linesLeft, linesAfter)
            } else if (linesLeft > 0) {
                res.add(lines[i])
                linesLeft--
            }
        }

        return ConsoleOutput(res.joinToString("\n"))
    }
}
