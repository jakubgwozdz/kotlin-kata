package kata.kotlin.bankocr

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class RecognitionKtTest {

    @Test
    fun bestGuessForDigit() {
        val resultFor7 = bestGuessForChar(listOf(
            " _ ",
            "  |",
            "  |",
            "   "))

        Assertions.assertThat(resultFor7).isInstanceOf(Digit::class.java)
        Assertions.assertThat((resultFor7 as Digit).digit).isEqualTo(7)
    }

    @Test
    fun bestGuessForFail() {
        val result = bestGuessForChar(listOf(
            " _ ",
            "  |",
            " _|",
            "   "))

        Assertions.assertThat(result).isInstanceOf(Unknown::class.java)
    }
}
