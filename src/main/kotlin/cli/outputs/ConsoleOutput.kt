package cli.outputs

class ConsoleOutput(value: String) : Output(value) {
    override fun sendOut() {
        if (value.isNotEmpty()) {
            println(value)
        }
    }

    override fun forNextCommand(): String = value
}
