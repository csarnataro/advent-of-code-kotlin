package y2015.d9

import java.io.File
import kotlin.collections.HashMap
import kotlin.collections.List
import kotlin.collections.Map
import kotlin.collections.copyOfRange
import kotlin.collections.distinct
import kotlin.collections.forEach
import kotlin.collections.forEachIndexed
import kotlin.collections.indices
import kotlin.collections.map
import kotlin.collections.mutableListOf
import kotlin.collections.plus
import kotlin.collections.set


fun getInput() = File("src/main/resources/y2015/d9/input.txt").useLines { it.toList() }

fun main() {
    val input = getInput()
    val distances = getDistancesAsTriples(input)
    val cities = getCities(distances)
    val cityIndexes = buildCityIndexes(cities)
    val matrix = buildDistanceMatrix(input, cityIndexes)
    // println(findPermutations(cities))
    val allDistances = findPermutations(cities).map{
        var sum = 0
        for (i in 0 .. it.size - 2) {
            print("${it[i]} > ")
            val dist = matrix[cityIndexes[it[i]]!!][cityIndexes[it[i+1]]!!]
            sum += dist
        }
        print("and finally ${it.last()}")
        println (" -> distance is: ${sum}")
        sum
    }
    println(allDistances.sortedDescending()[0])
}

/*
"London to Dublin = 464",
"London to Belfast = 518",
"Dublin to Belfast = 141"

(0,      464,    518,   101),
(464,    0,      141,   301),
(518,    141,    0,     201)
(101,    301,    201,   0)
*/

fun buildDistanceMatrix(input: List<String>, cityIndexes: Map<String, Int>): Array<Array<Int>> {
    val distances = getDistancesAsTriples(input)
    val cities = getCities(distances)

    val matrix = Array(cities.size) {
        Array(cities.size) { 0 }
    }
    distances.forEach {
        matrix[cityIndexes[it.first]!!][cityIndexes[it.second]!!] = it.third
        matrix[cityIndexes[it.second]!!][cityIndexes[it.first]!!] = it.third
        matrix[cityIndexes[it.first]!!][cityIndexes[it.first]!!] = 0
    }
    return matrix
}

fun buildCityIndexes(cities: List<String>): Map<String, Int> {
    val map = HashMap<String, Int>()
    cities.forEachIndexed { index, element ->
        map[element] = index
    }
    return map
}

fun getDistancesAsTriples(input: List<String>): List<Triple<String, String, Int>> {
    return input.map { it ->
        val result = Regex("(\\w+) to (\\w+) = (\\d+)").matchEntire(it)
        val (firstCity, secondCity, distance) = result!!.destructured
        Triple(firstCity, secondCity, distance.toInt())
    }
}

fun getCities(input: List<Triple<String, String, Int>>): List<String> {
    return (input.map { it.first } + input.map { it.second }).distinct()
}

fun <T> Array<T>.splice(index: Int): Array<T> {
    val firstPart = this.copyOfRange(0, index)
    val secondPart = this.copyOfRange(index + 1, this.size)

    return firstPart + secondPart
}

fun findPermutations(array: List<String>): List<List<String>> {
    val permutations: MutableList<List<String>> = mutableListOf()

    if (array.isEmpty()) {
        permutations.add(emptyList())
    } else {
        for (i in array.indices) {
            val subPerm = findPermutations(array.toTypedArray().splice(i).toList())
            subPerm.forEach {
                permutations.add(listOf(array[i]) + it)
            }
        }
    }
    return permutations
}


//fun findPermutations(array: IntArray): List<IntArray> {
//    val permutations = mutableListOf<IntArray>()
//
//    if (array.isEmpty()) {
//        permutations.add(intArrayOf())
//    } else {
//        for (i in array.indices) {
//            val subPerm = findPermutations(array.splice(i))
//            subPerm.forEach {
//                permutations.add(intArrayOf(array[i]) + it)
//            }
//        }
//    }
//    return permutations
//}

//fun combinations2(arr: Array<String?>, len: Int, startPosition: Int, result: Array<String?>) {
//    if (len == 0) {
//        println(Arrays.toString(result))
//        return
//    }
//    for (i in startPosition..arr.size - len) {
//        result[result.size - len] = arr[i]
//        combinations2(arr, len - 1, i + 1, result)
//    }
//}