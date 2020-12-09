package y2015.d5

import java.io.File

val VOWELS = listOf('a', 'e', 'i', 'o', 'u')
val FORBIDDEN_SEQUENCES = listOf("ab", "cd", "pq", "xy")

fun main() {
    val count = getInput().filter {
        isNice(it)
    }.size
    println(count)
}

fun isNice(string: String) : Boolean{

    return containsThreeVowels(string)
            && containsDoubleLetter(string)
            && doesntContainForbiddenSequence(string)
}

fun doesntContainForbiddenSequence(string: String): Boolean {
    return !FORBIDDEN_SEQUENCES.any {
        string.contains(it)
    }
}

fun containsDoubleLetter(string: String): Boolean {
    // don't need to loop over the whole string, we can stop after the first occurrence
    for (i in 1 until string.length) {
        if (string[i] == string[i-1]) return true
    }
    return false
}

fun containsThreeVowels(string: String): Boolean {
    // don't need to loop over the whole string, we can stop after the first 3 wovels
    var count = 0
    for (c in string) {
        if (isVowel(c)) {
            count++
            if (count == 3) {
                return true
            }
        }
    }
    return false
}

fun isVowel(char: Char) : Boolean {
    return VOWELS.contains(char)
}

fun getInput() = File("src/main/resources/y2015/d5/test-input.txt").useLines { it.toList() }
