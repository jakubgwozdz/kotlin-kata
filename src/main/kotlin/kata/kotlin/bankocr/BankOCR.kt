package kata.kotlin.bankocr

fun bankOCR(input: List<String>): String {

    // make some assertions about input data (contract-driven)
    assert(input.size == 4)
    assert(input.all { it.length == 27 })

    val transposed = (0 until input.first().length step 3) // offset of each new digit
        .map { i -> input.map { it.substring(i, i + 3) } } // substrings for given offset

    val recognized = transposed
        .map(::bestGuessForChar) // found letter

    val postFix = when {
        !recognized.all { it is Digit } -> " ILL"
        !checkSum(recognized.map { it as Digit }.map { it.digit }) -> " ERR"
        else -> ""
    }

    return recognized.joinToString(separator = "", postfix = postFix)
}

