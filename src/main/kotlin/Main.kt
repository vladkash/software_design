import cli.Cli
import cli.environments.MutableEnv

/**
 * Входная точка программы
 * в бесконечном цикле выполняем
 * команды, поступающие от пользователя
 */
fun main() {
    val cli = Cli(MutableEnv(System.getenv().toMutableMap()))
    while (true) {
        print(">>> ")
        val command = readln()
        cli.execute(command)
    }
}
