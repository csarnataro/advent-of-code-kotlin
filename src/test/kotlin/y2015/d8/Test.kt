package y2015.d8

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Test {
    @Test
    fun testCountInMemoryChars() {
        assertEquals(0, countInMemoryChars(""""""""))
        assertEquals(3, countInMemoryChars(""""abc""""))
        assertEquals(7, countInMemoryChars(""""aaa\"aaa""""))
        assertEquals(1, countInMemoryChars(""""\x27""""))
    }
    @Test
    fun testEncodedChars() {
        assertEquals(6, countEncodedChars("""
            ""
        """.trimIndent()))
        assertEquals(9, countEncodedChars("""
            "abc"
        """.trimIndent()))
        assertEquals(16, countEncodedChars("""
            "aaa\"aaa"
        """.trimIndent()))
        assertEquals(11, countEncodedChars("""
            "\x27"
        """.trimIndent()))
    }

}