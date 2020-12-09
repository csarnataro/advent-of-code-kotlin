package y2020.d3

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class Test {
    @Test
    fun testBuildMatrix() {
        val input = listOf(
            "..#.",
            "..#.",
            "#.#.",
        )
        val expected = arrayOf(
            arrayOf(true, true, false, true),
            arrayOf(true, true, false, true),
            arrayOf(false, true, false, true),
        )
        val actual = buildMatrix(input)
        assertArrayEquals(expected, actual)

    }

    @Test
    fun testProcessMatrix() {
        val input = arrayOf(
            arrayOf(true, true, false, true), // (true, true, false, true),
            arrayOf(true, true, false, true), // (true, true, false, true),
            arrayOf(false, true, false, true), // (false, true, false, true),
        )
        val actual = processMatrix(input)
        val expected = 1
        assertEquals(expected, actual)

    }

}