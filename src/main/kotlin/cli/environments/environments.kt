package cli.environments

/**
 * Немутабельная среда выполнения
 * хранит ключи и значения переменных, а также
 * может заменить в строке переменные на значения
 */
typealias Env = Map<String, String>

fun Env.replaceVars(statement: String): String =
    statement.replace(Regex("\\$\\w+")) { this[it.value.drop(1)] ?: "" }

/**
 * Мутабельная среда выполнения
 */
typealias MutableEnv = MutableMap<String, String>