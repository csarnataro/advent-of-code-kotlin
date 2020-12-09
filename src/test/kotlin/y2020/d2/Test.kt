package y2020.d2

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Test {
    @Test
    fun testParseInput() {
        val input = listOf(
            "1-3 a: abcde",
            "1-3 b: cdefg",
            "2-9 c: ccccccccc"
        )

        val actual = parseInput(input)
        val expected = listOf(
            PasswordRule(1, 3, "a","abcde"),
            PasswordRule(1, 3, "b", "cdefg"),
            PasswordRule(2, 9, "c","ccccccccc"),
        )

        assertEquals(expected, actual)
    }

    @Test
    fun testFilterTriples() {
        val input = listOf(
            PasswordRule(1, 3, "a","abcde"),
            PasswordRule(1, 3, "b", "cdefg"),
            PasswordRule(2, 9, "c","ccccccccc"),
        )

        val actual = filterTriples(input)
        val expected = listOf(
            PasswordRule(1, 3, "a","abcde"),
            PasswordRule(2, 9, "c","ccccccccc"),
        )

        assertEquals(expected, actual)

    }

    @Test
    fun testCheckFirstChar() {
        assertEquals(
            checkFirstChar(PasswordRule(1, 3, "a", "abcde")),
            true
        )
        assertEquals(
            checkFirstChar(PasswordRule(1, 3, "b", "abcde")),
            false
        )
    }

    @Test
    fun testCheckSecondChar() {
        assertEquals(
            checkSecondChar(PasswordRule(1, 3, "c", "abcde")),
            true
        )
        assertEquals(
            checkSecondChar(PasswordRule(1, 3, "b", "abcde")),
            false
        )
    }

}