package y2020.d6

fun main() {
    val input = getInputAsString()
    val allAnswersByGroup = getAllAnswersByGroupByPerson(input)
    val sizes = getAnwerSizes(allAnswersByGroup)
    val sum = sizes.sum()
    println(sum)
}

fun getAnwerSizes(allAnswersByGroup: List<Set<String>>): IntArray {
    return allAnswersByGroup.map {
        it.size
    }.toIntArray()
}

fun getAllAnswersByGroupByPerson(input: String): List<Set<String>> { // List<List<Set<String>>> {
    val answersSet = input.split("\n\n") // --> original input
        .map { it.split("\n") } // --> group by group
        .map { // --> each individual in the group
            it.map {
                it.fold(HashSet(), { acc: HashSet<String>, value ->
                    acc.add(value.toString())
                    acc
                })
            }
        }

    return answersSet.map {
        it.fold(('a'..'z').map{it.toString()}.toSet()) { acc, value ->
            acc intersect value
        }
    }
}
