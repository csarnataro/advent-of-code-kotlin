package y2020.d8

import java.lang.Exception

fun main() {
    val input = getInput("input")
    var instructions = buildInstructions(input)

    var tryVariations = false
    // test the original set fist
    try {
        val accumulator = execute(instructions)
        println(accumulator)

    } catch (e: Exception) {
        tryVariations = true
    }

    // maybe not ideal, I'm doing a "brute force" attempt to change every single jmp -> nop and viceversa
    // until i find a set of instructions which don't loop
    if (tryVariations) {
        for (index in 0 until instructions.size) {
            instructions = buildInstructions(input)
            try {
                if (instructions[index].instruction == "jmp") {
                    instructions[index].instruction = "nop"
                } else if (instructions[index].instruction == "nop") {
                    instructions[index].instruction = "jmp"
                }

                val accumulator = execute(instructions)
                println(accumulator)
                break


            } catch (e: Exception) {
                println("Found a loop executing instruction $index")
                continue
            }
        }
    }
}

fun execute(instructions: List<Instruction>): Int {
    val DEBUG_CAP = 10000
    var alreadyExecuted = mutableMapOf<Int, Boolean>() // instruction number, number of executions
    var accumulator = 0
    var pointer = 0
    var debugCounter = 0
    while (debugCounter < DEBUG_CAP && pointer < instructions.size) {
        // println("Exec instruction at[$pointer]: ${instructions[pointer].instruction} ${instructions[pointer].operand}")
        val result = executeInstruction(instructions[pointer], pointer, accumulator)
        if (alreadyExecuted[pointer] == true) {
            // do nothing atm
            throw Exception("Found a loop")
        }
        alreadyExecuted.putIfAbsent(pointer, true)
        pointer = result.nextPointer
        accumulator = result.newAccumulator
        debugCounter++
        if (debugCounter == DEBUG_CAP) {
            println(">>>>>> MAX CAP <<<<<<")

        }
    }

    return accumulator
}