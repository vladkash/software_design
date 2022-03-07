package cli.environments

class MutableEnv(vars: MutableMap<String, String>) : Env(vars) {
    fun put(key: String, value: String) {
        vars[key] = value
    }
}
