package y2015.d5

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Test {
    @Test
    fun testIsNice() {
        assertEquals(true, isNice("ugknbfddgicrmopn"))
        assertEquals(true, isNice("aaa"))

        assertEquals(false, isNice("a"))
        assertEquals(false, isNice("jchzalrnumimnmhp"))
        assertEquals(false, isNice("haegwjzuvuyypxyu"))
        assertEquals(false, isNice("dvszwmarrgswjxmb"))
    }

    @Test
    fun testIsTwiceNice() {
        assertEquals(true, isTwiceNice("qjhvhtzxzqqjkmpb"))
        assertEquals(true, isTwiceNice("xxyxx"))

        assertEquals(false, isTwiceNice("uurcxstgmygtbstg"))
        assertEquals(false, isTwiceNice("ieodomkazucvgmuy"))
    }
}