import cli.Cli
import cli.environments.MutableEnv

/**
 * Входная точка программы
 * в бесконечном цикле выполняем
 * команды, поступающие от пользователя
 */
fun main() {
    val location = System.getProperty("user.dir")
    val variables = System.getenv()
    val environment = MutableEnv(location, variables)
    val cli = Cli(environment)
    while (true) {
        print(">>> ")
        val command = readln()
        cli.execute(command)
    }
}
