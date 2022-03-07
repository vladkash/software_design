package cli.outputs

abstract class Output(protected val value: String) {
    abstract fun sendOut()

    abstract fun forNextCommand(): String
}
