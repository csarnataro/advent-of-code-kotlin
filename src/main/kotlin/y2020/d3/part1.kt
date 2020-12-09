package y2020.d3

import java.io.File

fun getInput() = File("src/main/resources/y2020/d3/input.txt").useLines { it.toList() }

fun main() {
    val matrix = buildMatrix(getInput())
    val result = processMatrix(matrix)
    println(result)
}

fun processMatrix(matrix: Array<Array<Boolean>>): Int {
    val height = matrix.size
    val width = matrix[0].size

    var row = 0
    var col = 0
    var countTree = 0
    while (row < height) {
        if (!matrix[row][col]) {
            countTree++
        }
        row++
        col = (col + 3) % width
    }
    return countTree
}

fun buildMatrix(input: List<String>): Array<Array<Boolean>> {
    return input.map {
        it.map { char ->
            char == '.'
        }.toTypedArray()
    }.toTypedArray()
}
