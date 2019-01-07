package kata.kotlin.bankocr

val digits = mapOf(
    1 to listOf(
        "   ",
        "  |",
        "  |",
        "   "),
    2 to listOf(
        " _ ",
        " _|",
        "|_ ",
        "   "),
    3 to listOf(
        " _ ",
        " _|",
        " _|",
        "   "),
    4 to listOf(
        "   ",
        "|_|",
        "  |",
        "   "),
    5 to listOf(
        " _ ",
        "|_ ",
        " _|",
        "   "),
    6 to listOf(
        " _ ",
        "|_ ",
        "|_|",
        "   "),
    7 to listOf(
        " _ ",
        "  |",
        "  |",
        "   "),
    8 to listOf(
        " _ ",
        "|_|",
        "|_|",
        "   "),
    9 to listOf(
        " _ ",
        "|_|",
        " _|",
        "   "),
    0 to listOf(
        " _ ",
        "| |",
        "|_|",
        "   "))

val digitsByStrings = digits.map { it.value to it.key }.toMap()





fun bankOCR(input: List<String>): String {

    // make some assertions about input data (contract-driven)
    assert(input.size == 4)
    val first = input.first()
    assert(first.length % 3 == 0)
    assert(input.all { it.length == first.length })


    val transposed = (0 until first.length step 3) // offset of each new digit
        .map { i -> input.map { it.substring(i, i + 3) } } // substrings for given offset

    val recognized = transposed
        .map(::bestGuess) // found letter

    return recognized.joinToString("")
}




sealed class GuessResult

data class Digit(val digit: Int) : GuessResult() {
    override fun toString(): String = digit.toString()
}

data class Unknown(val lines: List<String>) : GuessResult() {
    override fun toString() = "?"
}


fun bestGuess(s: List<String>) = digitsByStrings[s]?.let{ Digit(it) }?:Unknown(s)
