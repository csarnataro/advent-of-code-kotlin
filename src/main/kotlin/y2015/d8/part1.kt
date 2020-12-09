package y2015.d8

import java.io.File

fun getInput() = File("src/main/resources/y2015/d8/test-input.txt").useLines { it.toList() }

fun main() {
    val result = process(getInput())
    println(result)
}

fun process(input: List<String>): Long {
    var countInCodeChars = 0L;
    var countInMemoryChars = 0L
    input.forEach{
        countInCodeChars += it.length
        countInMemoryChars += countInMemoryChars(it)
    }
    return countInCodeChars - countInMemoryChars
}

fun countInMemoryChars(line: String): Long {
    val finalString = line
        .slice(1 until line.length - 1)
        .replace("\\\\", "S")
        .replace("\\\"", "Q")
        .replace(Regex("""\\x[0-9a-f][0-9a-f]"""), "R")
    // println("code was [$line] in memory [$finalString]")
    return finalString.length.toLong()
}
