package kata.kotlin.bankocr


sealed class GuessResult(val lines: List<String>)

class Digit(val digit: Int, lines: List<String>) : GuessResult(lines) {
    override fun toString(): String = digit.toString()
}

class Unknown(lines: List<String>) : GuessResult(lines) {
    override fun toString() = "?"
}


fun bestGuessForChar(s: List<String>) = digitsByStrings[s]?.let { Digit(it, s) } ?: Unknown(s)
