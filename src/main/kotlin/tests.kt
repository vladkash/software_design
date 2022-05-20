fun main() {
    val raw = "grep -i -w -A 2134 asasfk ylasidu lajsdh oiausdfjhl"

    val matches = Regex("(-\\w+)\\s+(\\w*)").findAll(raw)

    for (match in matches) {
        println(match.groupValues[1])
        println(match.groupValues[2])
    }
}
