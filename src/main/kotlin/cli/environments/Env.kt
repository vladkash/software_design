package cli.environments

open class Env(protected val vars: MutableMap<String, String>) {
    fun get(key: String) = vars[key]

    fun has(key: String) = vars.contains(key)

    fun replaceVars(statement: String): String =
        statement.replace(Regex("\\$\\w+")) { vars[it.value.drop(1)] ?: "" }
}
