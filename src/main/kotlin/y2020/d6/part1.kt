package y2020.d6

import java.io.File

fun main() {
    val input = getInputAsString()
    val allAnswersByGroup = getAllAnswersByGroup(input)
    val allAnswersNoDupes = getAllAnswersNoDupes(allAnswersByGroup)
    val countsAllAnswers = allAnswersNoDupes.map { it.length }
    val sum = countsAllAnswers.sum()

    println(sum)
}

fun getAllAnswersNoDupes(allAnswersByGroup: List<String>): List<String> {
    return allAnswersByGroup.map{
        it.toCharArray().distinct().joinToString("")
    }
}

fun getInputAsString() = File("src/main/resources/y2020/d6/input.txt")
    .readText()

fun getAllAnswersByGroup(input: String): List<String> {
    return input.split("\n\n")
        .map { it.replace("\n", "") }
}
