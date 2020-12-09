package y2015.d8

fun main() {
    val result = processPart2(getInput())
    println(result)
}

fun processPart2(input: List<String>): Long {
    var countOriginalChars = 0L;
    var countEncodedChars = 0L
    input.forEach{
        countOriginalChars += it.length
        countEncodedChars += countEncodedChars(it)
    }
    println("countEncodedChars[$countEncodedChars] - countOriginalChars[$countOriginalChars]")
    return countEncodedChars - countOriginalChars
}

fun countEncodedChars(line: String): Long {
    val finalString = line
        .replace(Regex("""\\x[0-9a-f][0-9a-f]"""), "XXXXX")
        .replace("\\", "SS")
        .replace("\"", "QQ") // quote
    println("code was [$line] in memory [$finalString]")
    return finalString.length.toLong() + 2 // quotes


}
