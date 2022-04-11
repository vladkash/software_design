package cli.commands

import cli.TestWithFakerAndEnv
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File
import java.nio.file.Files
import kotlin.io.path.absolutePathString
import kotlin.io.path.listDirectoryEntries
import kotlin.io.path.name

internal class LsTestWithFakerAndEnv : TestWithFakerAndEnv() {
    @Test
    fun testLsWithNoArguments() {
        val ls = LsCommand("", env)

        val expected = File(".").listFiles()
            ?.joinToString(" ") { it.name }
            ?: ""

        val actual = ls.run("").forNextCommand()
        assertEquals(expected, actual)
    }

    @Test
    fun testLsWithArgument() = withTempDir { dirName, expected ->
        val ls = LsCommand(dirName, env)

        val actual = ls.run("").forNextCommand()
        assertEquals(expected, actual)
    }

    private fun withTempDir(block: (dirName: String, expected: String) -> Unit) {
        val dir = Files.createTempDirectory("dirForLsTest")

        repeat(5) {
            Files.createTempFile(dir, "file$it", ".txt")
        }
        repeat(3) {
            Files.createTempDirectory(dir, "dir$it")
        }

        val dirContent = dir.listDirectoryEntries().joinToString(" ") { it.name }
        block(dir.absolutePathString(), dirContent)

        dir.toFile().deleteRecursively()
    }
}
