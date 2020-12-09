package y2020.d6

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test


class Test {
    @Test
    fun testGetAllAnswersNoDupes() {
        val input = listOf("abc", "abac", "x")
        val actual = getAllAnswersNoDupes(input)
        val expected = listOf("abc", "abc", "x")

        assertArrayEquals(expected.toTypedArray(), actual.toTypedArray())
    }

    @Test
    fun testGetAnswers() {
        val input = """
            abc
            
            a
            b
            c
            
            ab
            ac
            
            a
            a
            a
            a
            
            b
        """.trimIndent()

        val actual = getAllAnswersByGroup(input)
        val expected = listOf(
            "abc",
            "abc",
            "abac",
            "aaaa",
            "b"
        )

        assertArrayEquals(expected.toTypedArray(), actual.toTypedArray())
    }



}