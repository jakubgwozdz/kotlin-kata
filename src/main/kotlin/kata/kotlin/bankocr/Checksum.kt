package kata.kotlin.bankocr

fun checkSum(accNo: List<Int>): Boolean {

    val sum = accNo
        .mapIndexed { index, digit -> (9 - index) * digit }
        .sum()

    return sum % 11 == 0

}
