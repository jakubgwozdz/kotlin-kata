package kata.kotlin.bankocr

import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader

typealias InputData = List<String>
typealias OutputData = String
typealias InputAndOutput = Pair<InputData, OutputData>

fun parse(stream: InputStream): List<InputAndOutput> {
    val reader = BufferedReader(InputStreamReader(stream))
    val result = mutableListOf<InputAndOutput>()
    val current = mutableListOf<String>()

    reader.lineSequence().forEach {
        when {
            it.startsWith("=> ") -> {
                result += InputAndOutput(current.toList(), it.substring(3))
                current.clear()
            }
            else -> current += it
        }
    }

    return result.toList()

}

class Dummy

fun main(args: Array<String>) {
    Dummy().javaClass.getResourceAsStream("/BankOCR/use_case_1.txt").use { stream ->
        parse(stream).forEach { inputOutput ->
            inputOutput.first.forEach { println(it) }
            println("should be ${inputOutput.second}")
            val bankOCR = bankOCR(inputOutput.first)
            println("is        $bankOCR")
            println(if (bankOCR == inputOutput.second) "OK" else "FAIL")
            println("=====================================================")
        }
    }
}
