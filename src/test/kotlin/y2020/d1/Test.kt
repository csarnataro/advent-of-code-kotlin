package y2020.d1

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Test {

    @Test
    fun testProcess() {
        val input = listOf(
            1721,
            979,
            366,
            299,
            675,
            1456
        )
        val expected = 514579
        val actual = process(input)
        assertEquals(expected, actual)
    }
}
