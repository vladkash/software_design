package cli.commands

import cli.environments.MutableEnv

abstract class CommandWithArgs(
    protected var arg: String,
    final override val environment: MutableEnv
) : Command() {

    protected abstract val allowedFlags: Set<CommandFlag>
    protected val flags: Map<String, String>

    init {
        flags = extractFlags()
        if (checkQuoting()) {
            arg = environment.replaceVars(arg)
        }
    }

    // возвращает флаги потенциальные флаги команды
    protected open fun allowedFlags(): Set<CommandFlag> = emptySet()

    // функция, которая на основании флагов команды
    // вытаскивает их в мапу ключ -> значение
    private fun extractFlags(): Map<String, String> {
        val result = mutableMapOf<String, String>()
        for (flag in allowedFlags) {
            val match = Regex("-${flag.key}${if (flag.hasArg) "\\s+(\\w*)" else "()"}").find(arg)
            if (match != null) {
                result[flag.key] = match.groupValues[1]
                arg = arg.replace(match.value, "")
            }
        }
        arg = arg.trim()
        return result
    }

    // определение weak/strong quoting
    // после того, как вытащены все
    // флаги из команды
    private fun checkQuoting(): Boolean {
        if (arg.length >= 2) {
            val first = arg.first()
            val last = arg.last()

            if (
                (first == '\'' && last == '\'') ||
                (first == '"' && last == '"')
            ) {
                arg = arg.drop(1).dropLast(1)
                if (first == '"') {
                    return true
                }
            } else {
                return true
            }
        }
        return false
    }
}
