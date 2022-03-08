package cli.commands

import cli.environments.Env
import cli.environments.MutableEnv
import cli.outputs.ConsoleOutput
import cli.outputs.Output

interface Command {
    /**
     * Метод выполнения команды, которая не модифицирует среду
     * @param input входные данные, которые поступают по пайплайну
     * @param env среда, в которой выполняется программа
     */
    fun run(input: String, env: Env): Output {
        return ConsoleOutput("")
    }

    /**
     * Метод, который может реализовывать команда, изменяющая среду
     * @param env среда, которую можно изменять
     */
    fun modifyEnv(env: MutableEnv) {}
}
