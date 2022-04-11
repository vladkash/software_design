package cli.environments

/**
 * Мутабельная среда выполнения
 */
class MutableEnv(
    location: String,
    vars: MutableMap<String, String>
) : Env(location, vars) {
    fun put(key: String, value: String) {
        vars[key] = value
    }
}
