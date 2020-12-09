package y2020.d5

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Test {
    @Test
    fun testGetSeat() {
        var input = "FBFBBFFRLR"
        var expected = Seat(44, 5, 357)
        var actual = getSeat(input)
        assertEquals(expected, actual)

        input = "BFFFBBFRRR"
        actual = getSeat(input)
        expected = Seat(70, 7, 567)
        assertEquals(expected, actual)

        input = "FFFBBBFRRR"
        actual = getSeat(input)
        expected = Seat(14, 7, 119)
        assertEquals(expected, actual)


        input = "BBFFBBFRLL"
        actual = getSeat(input)
        expected = Seat(102, 4, 820)
        assertEquals(expected, actual)

    }

}