package y2015.d7

import java.io.File
import java.lang.Exception

fun getInput() = File("src/main/resources/y2015/d7/test-input.txt").useLines { it.toList() }

@ExperimentalUnsignedTypes
fun main() {
    val instructions = getInstructions()
    val registries = processInstructions(instructions)
    println(registries["a"])
}

fun processInstructions(instructions: List<Pair<String, Any>>): Map<String, UShort> {
    val registries = HashMap<String, UShort>()
    var i = 0
    while(i < instructions.size ) {
        var instruction = instructions[i]
        i++
        var variableName = instruction.first
        if (instruction.second is UShort && !registries.containsKey(variableName)) {
            registries[variableName] = instruction.second as UShort
            i = 0
            continue
        } else if (instruction.second is String  && !registries.containsKey(variableName)) { // variable name
            if (registries.containsKey(instruction.second)) {
                registries[variableName] = registries[instruction.second]!!
                i = 0;
                continue
            }
        } else if (instruction.second is Pair<*, *>  && !registries.containsKey(variableName)) { // NOT
            var otherVarName = (instruction.second as Pair<*, *>).second
            if (registries.containsKey(otherVarName)) {
                registries[variableName] = registries[otherVarName]!!.inv()
                i = 0
                continue
            }
        } else if (instruction.second is Triple<*, *, *>  && !registries.containsKey(variableName)) { // OTHER
            val operation = (instruction.second as Triple<*, *, *>)
            val operationName = operation.first
            val firstOperandName = operation.second
            val secondOperandName = operation.third
            // check if one of the 2 operands are numeric
            val firstOperand: UShort? = when {
                registries.containsKey(firstOperandName) -> registries[firstOperandName]!!
                else -> {
                    try { (firstOperandName as String).toUShort() } catch (e: Exception) { null }
                }
            }
            val secondOperand: UShort? = when {
                registries.containsKey(secondOperandName) -> registries[secondOperandName]!!
                else -> {
                    try { (secondOperandName as String).toUShort() } catch (e: Exception) { null }
                }
            }

            if (operationName == "AND") {
                if (firstOperand != null && secondOperand != null) {
                    registries[variableName] = firstOperand.and(secondOperand)
                    i = 0;
                    continue;
                }
            }
            if (operationName == "OR") {
                if (firstOperand != null && secondOperand != null) {
                    registries[variableName] = firstOperand.or(secondOperand)
                    i = 0;
                    continue;
                }
            }
            if (operationName == "LSHIFT") {
                if (firstOperand != null && secondOperand != null) {
                    registries[variableName] = firstOperand.toInt().shl(secondOperand.toInt()).toUShort()
                    i = 0;
                    continue;
                }
            }
            if (operationName == "RSHIFT") {
                if (firstOperand != null && secondOperand != null) {
                    registries[variableName] = firstOperand.toInt().ushr(secondOperand.toInt()).toUShort()
                    i = 0;
                    continue;
                }
            }
        }
    }
    return registries

}

fun getInstructions(): List<Pair<String, Any>> = getInput().map {
    parseLineIntoInstruction(it)
}

fun parseLineIntoInstruction(line: String): Pair<String, Any> {
/*
NOT dq -> dr        not // unary
kg OR kf -> kh
ep OR eo -> eq      or  // binary
44430 -> b          assignment // 0-ary => INITIAL
NOT gs -> gt
dd OR do -> dp
eg AND ei -> ej
y AND ae -> ag
jx AND jz -> ka
lf RSHIFT 2 -> lg
 */
    val sides = line.split("->")
    val expression = sides[0].trim()
    val variable = sides[1].trim()

    val tokens = expression.split(" ")
    val operation = when {
        expression.startsWith("NOT") ->
            Pair("NOT", tokens[1])

        expression.contains("AND") ->
            Triple("AND", tokens[0], tokens[2])

        expression.contains("OR") ->
            Triple("OR", tokens[0], tokens[2])

        expression.contains("LSHIFT") ->
            Triple("LSHIFT", tokens[0], tokens[2])

        expression.contains("RSHIFT") ->
            Triple("RSHIFT", tokens[0], tokens[2])

        else -> {
            try {
                tokens[0].toUShort()
            } catch (e: Exception) {
                tokens[0]
            }
        }

    }

    return Pair(variable, operation)


}


// Instruction(destinationChannel: Channel, operation: Operation)

// Operation(operator: String, operand1: Operand, operand2: Operand)
//    apply(): Int

// Operand : Channel | Integer

// Map of channels

/*
    - build instruction list based on input
    - loop over instruction list
    - find assignments
    - remove assignments from instruction list
    - apply all operations where all operators have a value
    - remove these operations
    - repeat until list is empty
 */

