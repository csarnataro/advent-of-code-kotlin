package y2020.d3

fun main() {
    val matrix = buildMatrix(getInput())
    val slopes = listOf(
        Pair(1, 1),
        Pair(3, 1),
        Pair(5, 1),
        Pair(7, 1),
        Pair(1, 2),
    )

    val result = slopes.fold(1) {
        acc, slope -> acc * processMatrixPart2(matrix, slope)
    }

    println(result)
}

fun processMatrixPart2(matrix: Array<Array<Boolean>>, slope: Pair<Int, Int>): Int {
    val height = matrix.size
    val width = matrix[0].size

    var row = 0
    var col = 0
    var countTree = 0
    while (row < height) {
        if (!matrix[row][col]) {
            countTree++
        }
        row += slope.second
        col = (col + slope.first) % width
    }
    println(countTree)
    return countTree
}

