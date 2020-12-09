package y2015.d7

import java.io.File

@ExperimentalUnsignedTypes
fun main() {
    val instructions = getInstructions()
    val registries = processInstructions(instructions)
    println(registries["a"])
}

