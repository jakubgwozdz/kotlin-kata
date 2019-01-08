package kata.kotlin.bankocr

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.URL
import java.util.stream.Stream
import kotlin.test.Ignore

internal class BankOCRKtTest {

    @TestFactory
    @Ignore
    fun useCase1(): Stream<DynamicTest> {
        val resource = this.javaClass.getResource("/bankOCR/use_case_1.txt")
        return generateTestCases(resource)
    }

    @TestFactory
    fun useCase3(): Stream<DynamicTest> {
        val resource = this.javaClass.getResource("/bankOCR/use_case_3.txt")
        return generateTestCases(resource)
    }

    @TestFactory
    fun useCase4(): Stream<DynamicTest> {
        val resource = this.javaClass.getResource("/bankOCR/use_case_4.txt")
        return generateTestCases(resource)
    }


    private fun generateTestCases(url: URL): Stream<DynamicTest> {
        return url.openStream().use(this::parse)
            .mapIndexed { i: Int, data: Pair<List<String>, String> ->
                DynamicTest.dynamicTest("test ${i + 1} should return '${data.second}'", url.toURI()) {
                    testSingleCase(data.first, data.second)
                }
            }.stream()
    }

    fun testSingleCase(data: List<String>, expected: String) {
        assertEquals(expected, bankOCR(data))
    }

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

