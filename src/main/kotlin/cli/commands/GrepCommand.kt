package cli.commands

import cli.environments.MutableEnv
import cli.outputs.ConsoleOutput
import cli.outputs.Output
import java.lang.Integer.max

class GrepCommand(
    arg: String,
    environment: MutableEnv
) : CommandWithArgs(arg, environment) {

    companion object {
        const val IGNORE_CASE_FLAG = "i"
        const val FULL_WORDS_FLAG = "w"
        const val LINES_AFTER_MATCH_FLAG = "A"
    }

    override val allowedFlags: Set<CommandFlag>
        get() = setOf(
            CommandFlag(IGNORE_CASE_FLAG, false),
            CommandFlag(FULL_WORDS_FLAG, false),
            CommandFlag(LINES_AFTER_MATCH_FLAG, true)
        )

    override fun run(input: String): Output {
        val options = mutableSetOf<RegexOption>()
        if (flags.containsKey(IGNORE_CASE_FLAG)) {
            options.add(RegexOption.IGNORE_CASE)
        }
        var pattern = arg
        if (flags.containsKey(FULL_WORDS_FLAG)) {
            pattern = "\\b($pattern)\\b"
        }
        val regex = Regex(pattern, options)

        val res = mutableListOf<String>()

        var linesAfter = flags[LINES_AFTER_MATCH_FLAG]?.toInt()
        if (linesAfter == null) {
            linesAfter = 0
        }
        var linesLeft = 0

        val lines = input.lines()
        for (i in lines.indices) {
            if (regex.find(lines[i]) != null) {
                res.add(lines[i])
                linesLeft = max(linesLeft, linesAfter)
            } else if (linesLeft > 0) {
                res.add(lines[i])
                linesLeft--
            }
        }

        return ConsoleOutput(res.joinToString("\n"))
    }
}
