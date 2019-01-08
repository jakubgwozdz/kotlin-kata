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

