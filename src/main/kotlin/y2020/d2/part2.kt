package y2020.d2

import java.io.File

fun main() {
    // read input
    val input = getInput()

    // parse input in Triples ... min occur, max occur, password
    val triples = parseInput(input)

    // filter out invalid passwords
    val validPasswords = filterTriplesPart2(triples)

    println(validPasswords.size)
}

fun filterTriplesPart2(triples: List<PasswordRule>): List<PasswordRule> {
    return triples.filter {
        // val newString = it.password.replace(Regex("[^${it.letter}]"), "")
        // newString.length >= it.min && newString.length <= it.max
        val firstCharOk = checkFirstChar(it)
        val secondCharOK = checkSecondChar(it)
        firstCharOk xor secondCharOK
    }
}

fun checkFirstChar(it: PasswordRule) = it.password[it.min - 1].toString() == it.letter

fun checkSecondChar(it: PasswordRule) = it.password[it.max - 1].toString() == it.letter


