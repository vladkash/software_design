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
open class CatCommand(private val arg: String, private val weakQuoting: Boolean) : FileCommand() {
    override fun run(input: String, env: Env): Output {
        return ConsoleOutput(
            if (arg.isEmpty()) input else readFile(if (weakQuoting) env.replaceVars(arg) else arg)
        )
    }
}

/**
 * Команда echo, выодит переданный аргумент, игнорирует pipeline
 */
class EchoCommand(private val arg: String, private val weakQuoting: Boolean) : Command {
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
        return ConsoleOutput(System.getProperty("user.dir"))
    }
}

/**
 * Команда wc, вывод статистики по символам, словам и строкам
 */
class WcCommand(private val arg: String, private val weakQuoting: Boolean) : FileCommand() {
    override fun run(input: String, env: Env): Output {
        val content = if (arg.isEmpty()) input else readFile(if (weakQuoting) env.replaceVars(arg) else arg)
        return ConsoleOutput(getCounts(content))
    }

    private fun getCounts(string: String): String = "${string.lines().filter { it.isNotEmpty() }.size}       ${string.trim().split(Regex("\\s+")).size}       ${string.length}       "
}