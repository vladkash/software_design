package cli

import cli.commands.CommandParser
import cli.commands.CommandRunningException
import cli.environments.MutableEnv
import cli.outputs.Output

/**
 * Основной класс, который обрабатывает поступющие команды
 * @param env Переменные среды, в которой работает cli
 */
class Cli(private val env: MutableEnv) {

    private val commandParser = CommandParser()

    /**
     * Функция обработки команды. Делит ее по ';' на пайплайны
     * а после по '|' на отдельные команды, осуществляет их парсинг, выполнение
     * и передачу аргументов по пайплану
     */
    fun execute(str: String) {

        str.split(';').forEach { pipeline ->

            val commands = pipeline.split('|')
                .map { commandParser.parse(it.trim()) }
                .map { it(env) } // передаем в каждую команду ссылку на environment

            try {
                var output: Output = commands.first().run("")

                // изменяются переменные среды, только если они
                // одной командой, не в пайплайне по примеру bash
                if (commands.size == 1) {
                    commands.first().modifyEnv()
                }

                for (i in 1 until commands.size) {
                    output = commands[i].run(output.forNextCommand())
                }

                output.sendOut()
            } catch (e: CommandRunningException) {
                println("Error while command running: ${e.message}")
            } catch (e: Exception) {
                println("Unhandled exception while command running: ${e.message}")
            }
        }
    }
}
