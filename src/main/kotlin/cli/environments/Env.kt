package cli.environments

/**
 * Немутабельная среда выполнения
 * хранит ключи и значения переменных, а также
 * может заменить в строке переменные на значения
 */
open class Env(
    var location: String,
    protected val vars: MutableMap<String, String>
) {
    fun get(key: String) = vars[key]

    fun has(key: String) = vars.contains(key)

    fun replaceVars(statement: String): String =
        statement.replace(Regex("\\$\\w+")) { vars[it.value.drop(1)] ?: "" }
}
