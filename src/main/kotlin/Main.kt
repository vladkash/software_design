import cli.Cli

/**
 * Входная точка программы
 * в бесконечном цикле выполняем
 * команды, поступающие от пользователя
 */
fun main() {
    val cli = Cli(System.getenv().toMutableMap())
    while (true) {
        print(">>> ")
        val command = readln()
        cli.execute(command)
    }
}
