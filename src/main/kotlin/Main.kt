import cli.Cli
import cli.environments.MutableEnv

fun main() {

    val cli = Cli(MutableEnv(System.getenv().toMutableMap()))
    while (true) {
        print(">>> ")
        val command = readln()
        cli.execute(command)
    }
}
