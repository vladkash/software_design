package cli.outputs

/**
 * Абстрактный класс вывода. С его помощью можно расширить
 * команды выводом в файл
 */
abstract class Output(protected val value: String) {

    /**
     * Окончательный вывод результата работы, например запись в консоль или файл
     */
    abstract fun sendOut()

    /**
     * Получение аргумента для следующей команды в пайплайне
     */
    abstract fun forNextCommand(): String
}
