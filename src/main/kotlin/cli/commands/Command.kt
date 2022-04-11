package cli.commands

import cli.environments.Env
import cli.outputs.ConsoleOutput
import cli.outputs.Output

abstract class Command {
    abstract val environment: Env

    /**
     * Метод выполнения команды, которая не модифицирует среду
     * @param input входные данные, которые поступают по пайплайну
     */
    open fun run(input: String): Output {
        return ConsoleOutput("")
    }

    /**
     * Метод, который может реализовывать команда, изменяющая среду
     */
    open fun modifyEnv() {}
}
