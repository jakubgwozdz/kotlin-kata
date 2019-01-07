package kata.kotlin.bankocr

import org.junit.jupiter.api.*

import org.junit.jupiter.api.Assertions.*
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import kotlin.streams.toList

internal class BankOCRKtTest {

    @TestFactory
    fun bankOCRTests(): List<DynamicContainer> {

        val allUseCases = this.javaClass
            .getResource("/bankOCR/")
            ?.toURI()
            ?.let { Paths.get(it) }
            ?.let { dir -> Files.list(dir).use { it.filter { path -> path.isUseCase() }.toList() } }
            ?: emptyList<Path>()

        assertNotEquals(emptyList<Path>(), allUseCases)

        return allUseCases.map { path ->
            val nodes = Files.newInputStream(path).use(this::parse)
                .mapIndexed { i: Int, data: Pair<List<String>, String> ->
                    DynamicTest.dynamicTest("test ${i + 1} should return '${data.second}'") {
                        testSingleCase(data.first, data.second)
                    }
                }.stream()

            DynamicContainer.dynamicContainer(path.fileName.toString(), path.toUri(), nodes)
        }
    }

    fun testSingleCase(data: List<String>, expected: String) {
        assertEquals(expected, bankOCR(data))
    }


    private fun Path.isUseCase() = fileName.toString().matches(".+\\.txt".toRegex())

    private fun parse(stream: InputStream): List<Pair<List<String>, String>> {
        val reader = BufferedReader(InputStreamReader(stream))
        val result = mutableListOf<Pair<List<String>, String>>()
        val current = mutableListOf<String>()

        reader.lineSequence().forEach {
            when {
                it.startsWith("=> ") -> {
                    result += current.toList() to it.substring(2).trim()
                    current.clear()
                }
                else -> current += it
            }
        }

        return result.toList()

    }

}

