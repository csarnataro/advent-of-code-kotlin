package y2020.d4

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class Test {

    @Test
    fun testGetPassportLines() {
        val input = """
ecl:gry pid:860033327 eyr:2020 hcl:#fffffd
byr:1937 iyr:2017 cid:147 hgt:183cm

iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884
hcl:#cfa07d byr:1929
""".trimIndent()

        val expected = listOf(
            "ecl:gry pid:860033327 eyr:2020 hcl:#fffffd byr:1937 iyr:2017 cid:147 hgt:183cm",
            "iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884 hcl:#cfa07d byr:1929"
        )
        val actual = getPassportsFromOneLineStrings(input)

        assertEquals(expected, actual)

    }

    @Test
    fun testValidPassport() {
        var input = Passport(
            mapOf<String, Any>(
                "ecl" to "gry",
                "pid" to "860033327",
                "eyr" to 2020,
                "hcl" to "#fffffd",
                "byr" to 1937,
                "iyr" to 2017,
                "cid" to "147",
                "hgt" to "183cm"
            )
        )
        var actual = isValid(input)

        assertTrue(actual)

        input = Passport(
            mapOf<String, Any>(
                "hcl" to "#ae17e1",
                "iyr" to "2013",
                "eyr" to "2024",
                "ecl" to "brn",
                "pid" to "760753108",
                "byr" to "1931",
                "hgt" to "179cm",
            )
        )
        actual = isValid(input)
        assertTrue(actual)
    }

    @Test
    fun testInvalidPassport() {
        var input = Passport(
            mapOf<String, Any>(
                "iyr" to 2013,
                "ecl" to "amb",
                "cid" to "350",
                "eyr" to 2023,
                "pid" to "028048884",
                "hcl" to "#cfa07d",
                "byr" to 1929,
            )
        )
        var actual = isValid(input)

        assertFalse(actual)

        input = Passport(
            mapOf<String, Any>(
                "hcl" to "#cfa07d",
                "eyr" to "2025",
                "pid" to "166559648",
                "iyr" to "2011",
                "ecl" to "brn",
                "hgt" to "59in",
            )
        )
        actual = isValid(input)
        assertFalse(actual)
    }

    @Test
    fun testParsePassport1() {
        val expected = Passport(
            mapOf<String, Any>(
                "ecl" to "gry",
                "pid" to "860033327",
                "eyr" to 2020,
                "hcl" to "#fffffd",
                "byr" to 1937,
                "iyr" to 2017,
                "cid" to "147",
                "hgt" to "183cm"
            )
        )
        val input = "ecl:gry pid:860033327 eyr:2020 hcl:#fffffd byr:1937 iyr:2017 cid:147 hgt:183cm"
        val actual = parsePassport(input)
        assertEquals(
            expected.toString(),
            actual.toString()
        )
    }

    @Test
    fun testParsePassport2() {
        val input = "iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884 hcl:#cfa07d byr:1929"
        val expected = Passport(
            mapOf<String, Any>(
                "iyr" to 2013,
                "ecl" to "amb",
                "eyr" to 2023,
                "cid" to "350",
                "pid" to "028048884",
                "hcl" to "#cfa07d",
                "byr" to 1929
            )
        )
        val actual = parsePassport(input)

        assertEquals(
            expected.toString(),
            actual.toString()
        )
    }

    @Test
    fun testEmptyPassport() {
        val input = ""
        val expected = Passport(emptyMap<String, Any>())
        val actual = parsePassport(input)
        assertEquals(expected, actual)
    }

    @Test
    fun testValidPassportForPart2() {
        val input = Passport(
            mapOf(
                "pid" to "087499704",
                "hgt" to "74in",
                "ecl" to "grn",
                "iyr" to 2012,
                "eyr" to 2030,
                "byr" to 1980,
                "hcl" to "#623a2f",
            )
        )
        val actual = isValidPart2(input)
        assertTrue(actual)
    }
}