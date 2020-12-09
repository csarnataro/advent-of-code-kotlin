package y2015.d5

fun main() {
    val count = getInput().filter {
        isTwiceNice(it)
    }.size
    println(count)
}

fun isTwiceNice(string: String): Boolean {
    return containsDoubleNotOverlappingSequences(string)
            && containsLetterWithAnotherBetween(string)
}

/**
 * It contains at least one letter which repeats with exactly one letter between them,
 * like xyx, abcdefeghi (efe), or even aaa.
 */
fun containsLetterWithAnotherBetween(string: String): Boolean {
    // qjhvhtzxzqqjkmpb -> hvh
    val lastIndex = string.length - 1
    for (i in 0..lastIndex - 2) {
        if (string[i] == string[i+2]) {
            return true
        }
    }

    return false
}

/**
 * It contains a pair of any two letters that appears at least twice in the string without overlapping,
 * like xyxy (xy) or aabcdefgaa (aa), but not like aaa (aa, but it overlaps).
 */
fun containsDoubleNotOverlappingSequences(string: String): Boolean {
    // qjhvhtzxzqqjkmpb
    // qj -> check in hvhtzxzqqjkmpb
    // jh -> check in vhtzxzqqjkmpb
    // ...
    // km -> pb
    // mp -> b      <--- is this useful?
    val lastIndex = string.length - 1
    for (i in 0..lastIndex - 2) {
        var stringToCheck = string.substring(i..i + 1)
        var checkIn = string.subSequence(i + 2..lastIndex)
        if (checkIn.contains(stringToCheck)) {
            return true
        }
    }
    return false

}
