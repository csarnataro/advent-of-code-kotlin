package y2015.d9

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Test {

//    @Test
//    fun testSplice() {
//        val expected = intArrayOf(1, 2, 5, 6)
//        val actual = intArrayOf(1, 2, 4, 5, 6).splice(2)
//        assertArrayEquals(expected, actual)
//    }
//
//    @Test
//    fun testBuildMatrix() {
//        val input = listOf(
//            "London to Dublin = 464",
//            "London to Belfast = 518",
//            "Dublin to Belfast = 141"
//        )
//        val actual = buildDistanceMatrix(input)
//
//        var expected = arrayOf(
//            //              L,      D,      B
//            /* L */ arrayOf(0, 464, 518),
//            /* D */ arrayOf(464, 0, 141),
//            /* B */ arrayOf(518, 141, 0)
//        )
//
//        assertArrayEquals(expected, actual)
//
//    }
//
//    @Test
//    fun testGetFullDistances() {
//        val input = listOf(
//            "London to Dublin = 464",
//            "London to Belfast = 518",
//            "Dublin to Belfast = 141"
//        )
//        val actual = getDistancesAsTriples(input)
//        val expected = listOf(
//            Triple("London", "Dublin", 464),
//            Triple("London", "Belfast", 518),
//            Triple("Dublin", "Belfast", 141)
//        )
//
//        assertEquals(expected, actual)
//    }
//
//
//    @Test
//    fun testGetCitiesFromInput() {
//        val input = listOf(
//            Triple("London", "Dublin", 464),
//            Triple("London", "Belfast", 518),
//            Triple("Dublin", "Belfast", 141)
//        )
//        val actual = getCities(input).sorted()
//        val expected = listOf(
//            "London",
//            "Dublin",
//            "Belfast"
//        ).sorted()
//
//        assertEquals(expected, actual)
//
//    }
//
//    @Test
//    fun testGetIndexesForCities() {
//        val input = listOf("London", "Dublin", "Belfast")
//        val expected = mapOf(
//            "London" to 0,
//            "Dublin" to 1,
//            "Belfast" to 2
//        )
//        val actual = buildCityIndexes(input)
//        assertEquals(expected, actual)
//    }
//
//    @Test
//    fun testGetDistancesAsInt() {
//        val input = arrayOf(
//            //              L,      D,      B
//            /* L */ arrayOf(0, 464, 518),
//            /* D */ arrayOf(464, 0, 141),
//            /* B */ arrayOf(518, 141, 0)
//        )
//        val actual = getDistancesAsInt(input)
//        val expected = intArrayOf(464, 518, 141)
//
//        assertArrayEquals(expected, actual)
//    }
//
//    @Test
//    fun testFindCombinations() {
//        val input = intArrayOf(1,2,3,4,5,6)
//        println(combinations(input, 3))
//    }
//
//    @Test
//    fun testFindPermutations() {
//        val input = intArrayOf(0, 1)
//        val expected = listOf(
//            intArrayOf(0, 1),
//            intArrayOf(1, 0)
//        )
//        val actual = findPermutations(input)
//        println(expected)
//        println(actual)
//
//    }
//
//    @Test
//    fun testFindPermutations2() {
//        val input = intArrayOf(0, 1, 2)
//        val expected = listOf(
//            intArrayOf(0, 1, 2),
//            intArrayOf(0, 2, 1),
//            intArrayOf(1, 0, 2),
//            intArrayOf(1, 2, 0),
//            intArrayOf(2, 0, 1),
//            intArrayOf(2, 1, 0),
//        )
//        val actual = findPermutations(input)
//        println(">>>>")
//        expected.map {
//            println(it.toTypedArray().contentToString())
//        }
//        println("====")
//        actual.map {
//            println(it.toTypedArray().contentToString())
//        }
//        println("<<<<")
//
//    }

}