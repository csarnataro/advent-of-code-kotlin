package y2020.d2

import java.io.File

fun getInput() = File("src/main/resources/y2020/d2/input.txt").useLines { it.toList() }

fun main() {
    // read input
    val input = getInput()

    // parse input in Triples ... min occur, max occur, password
    val triples = parseInput(input)

    // filter out invalid passwords
    val validPasswords = filterTriples(triples)

    println(validPasswords.size)
}

fun filterTriples(triples: List<PasswordRule>): List<PasswordRule> {
    return triples.filter { it ->
        val newString = it.password.replace(Regex("[^${it.letter}]"), "")
        newString.length >= it.min && newString.length <= it.max
    }
}

data class PasswordRule(val min: Int, val max: Int, val letter: String, val password: String)

fun parseInput(input: List<String>): List<PasswordRule> {
    return input.map {
        val tokens = it.split(" ")
        val min = tokens[0].split("-")[0].toInt()
        val max = tokens[0].split("-")[1].toInt()

        PasswordRule(min, max, tokens[1].slice(0 until tokens[1].length - 1), tokens[2])
    }
}


