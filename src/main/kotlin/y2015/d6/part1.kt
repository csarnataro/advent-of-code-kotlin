package y2015.d6

import java.io.File

const val MATRIX_SIZE = 1000
val matrix = Array(MATRIX_SIZE * MATRIX_SIZE, init = {i:Int -> false})

fun main() {
    val instructions = getInstructions();
    instructions.forEach {
        applyOperation(matrix, it)
    }
    println(matrix.filter { it }.size)
}

fun getInstructions(): List<Instruction> {
    return getInput().map {
        parseLineIntoInstruction(it)
    }
}

fun <T> getValueAt(matrix: Array<T>, x: Int, y: Int): T = matrix[((y - 1) * MATRIX_SIZE) + x]
fun <T> setValueAt(matrix: Array<T>, x: Int, y: Int, calculateValue: (currentValue: T) -> T) = run {
    val currentValue = matrix[((y - 1) * MATRIX_SIZE) + x]
    matrix[((y - 1) * MATRIX_SIZE) + x] = calculateValue(currentValue)
}

fun applyOperation(matrix: Array<Boolean>, instruction: Instruction, ) {
    val (operation, topLeftCorner, bottomRightCorner) = instruction
    for (x in topLeftCorner[0]..bottomRightCorner[0]) {
        for (y in topLeftCorner[1]..bottomRightCorner[1]) {
            setValueAt(matrix, x, y) {
                when (operation) {
                    Operation.ON -> true
                    Operation.OFF -> false
                    Operation.TOGGLE -> !it
                }
            }
        }
    }
}

/**
 *
 * turn off 199,133 through 461,193
 * toggle 537,781 through 687,941
 * turn on 226,196 through 599,390
 */
fun parseLineIntoInstruction(line: String): Instruction {
    val tokens = line.split(" ")
    return when {
        line.startsWith("turn on") -> {
            Instruction(Operation.ON, getCorners(tokens[2]), getCorners((tokens[4])))
        }
        line.startsWith("turn off") -> {
            Instruction(Operation.OFF, getCorners(tokens[2]), getCorners((tokens[4])))
        }
        line.startsWith("toggle") -> {
            Instruction(Operation.TOGGLE, getCorners(tokens[1]), getCorners((tokens[3])))
        }
        else -> throw IllegalArgumentException("Invalid color param value")
    }
}

fun getCorners(asString: String) =
    asString.split(",").map { it.toInt() }.toTypedArray().toIntArray()

interface ApplyOperation<T> { fun applyOp(current: T): T }

enum class Operation {
    ON,
    OFF,
    TOGGLE
}


data class Instruction(val command: Operation, val topLeftCorner: IntArray, val bottomRightCorner: IntArray) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Instruction

        if (command != other.command) return false
        if (!topLeftCorner.contentEquals(other.topLeftCorner)) return false
        if (!bottomRightCorner.contentEquals(other.bottomRightCorner)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = command.hashCode()
        result = 31 * result + topLeftCorner.contentHashCode()
        result = 31 * result + bottomRightCorner.contentHashCode()
        return result
    }

}

fun getInput() = File("src/main/resources/y2015/d6/test-input.txt").useLines { it.toList() }
