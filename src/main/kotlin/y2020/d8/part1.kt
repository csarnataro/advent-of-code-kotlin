package y2020.d8

import java.io.File

fun getInput(filename: String) = File("src/main/resources/y2020/d8/${filename}.txt").useLines { it.toList() }

fun main() {
    val input = getInput("input")
    val instructions = buildInstructions(input)
    val accumulator = executeUntilFirstLoop(instructions)
    println(accumulator)
}

fun buildInstructions(instructionsAsStrings: List<String>): MutableList<Instruction> {
    return instructionsAsStrings.map { buildInstruction(it) }.toMutableList()
}

fun buildInstruction(instructionAsString: String): Instruction {
    val tokens = instructionAsString.split(" ")
    return Instruction(tokens[0], tokens[1].toInt())
}

fun executeUntilFirstLoop(instructions: List<Instruction>): Int {
    var alreadyExecuted = mutableMapOf<Int, Boolean>() // instruction number, number of executions
    var accumulator = 0
    var firstLoop = true
    var pointer = 0
    var debugCounter = 0
    while (true && firstLoop) {
        val result = executeInstruction(instructions[pointer], pointer, accumulator)
        alreadyExecuted.putIfAbsent(pointer, true)
        pointer = result.nextPointer
        accumulator = result.newAccumulator

        debugCounter++
        if (debugCounter == 20000) {
            println(">>>>>> REACHED MAX NUMBER OF INSTRUCTIONS [$debugCounter] <<<<<<")
            firstLoop = false
        }

        if (checkInstructionNotExecutedYet(alreadyExecuted, pointer)) {
            firstLoop = false
        }
    }

    return accumulator
}

fun checkInstructionNotExecutedYet(alreadyExecuted: MutableMap<Int, Boolean>, pointer: Int): Boolean {
    return alreadyExecuted[pointer] ?: false

}

fun executeInstruction(instruction: Instruction, pointer: Int, accumulator: Int): InstructionResult {
    return when(instruction.instruction) {
        "jmp" -> InstructionResult(
            nextPointer = pointer + instruction.operand,
            newAccumulator = accumulator)
        "acc" -> InstructionResult(
            nextPointer = pointer + 1,
            newAccumulator = accumulator + instruction.operand
        )
        else -> InstructionResult(
            nextPointer = pointer + 1,
            newAccumulator = accumulator
        )
    }
}

data class Instruction(var instruction: String, val operand: Int)
data class InstructionResult(val newAccumulator: Int, val nextPointer: Int)
