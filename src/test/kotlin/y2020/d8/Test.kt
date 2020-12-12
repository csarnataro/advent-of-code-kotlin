package y2020.d8

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class Test {

    @Test
    fun testBuildInstruction() {
        val input = "jmp -20"
        val actual = buildInstruction(input)
        val expected = Instruction("jmp", -20)
        assertEquals(expected, actual)
    }

        @Test
    fun testBuildInstructions() {
        val input = """
            nop +0
            acc +1
            jmp +4
            jmp -20
        """.trimIndent().split("\n")
        val actual = buildInstructions(input)
        val expected = listOf(
            Instruction("nop", 0),
            Instruction("acc", 1),
            Instruction("jmp", 4),
            Instruction("jmp", -20),
        )

        assertArrayEquals(
            expected.toTypedArray(),
            actual.toTypedArray()
        )
    }
}