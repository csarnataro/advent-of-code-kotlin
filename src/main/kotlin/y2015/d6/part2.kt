package y2015.d6

import kotlin.math.max

val brightnessMatrix = Array(MATRIX_SIZE * MATRIX_SIZE, init = {0.0})

fun main() {
    val instructions = getInstructions();
    instructions.forEach {
        applyContinuousOperation(brightnessMatrix, it)
    }
    var sum = 0.0
    for (x in 1 until MATRIX_SIZE) {
        for (y in 1 until MATRIX_SIZE) {
            sum += getValueAt(brightnessMatrix, x, y)
        }
    }
    println(sum)
}

fun applyContinuousOperation(matrix: Array<Double>, instruction: Instruction) {
    val (operation, topLeftCorner, bottomRightCorner) = instruction
    for (x in topLeftCorner[0]..bottomRightCorner[0]) {
        for (y in topLeftCorner[1]..bottomRightCorner[1]) {
            setValueAt(matrix, x, y) {
                when (operation) {
                    Operation.ON -> it + 1
                    Operation.OFF -> max(it - 1, 0.0)
                    Operation.TOGGLE -> it + 2
                }
            }
        }
    }
}



