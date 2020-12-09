package y2020.d4

import java.io.File

enum class MandatoryFields {
    byr {
        override fun validate(value: String): Boolean {
            return isValidBirthYear(value)
        }
    },
    iyr {
        override fun validate(value: String): Boolean {
            return isValidIssueYear(value)
        }
    },
    eyr {
        override fun validate(value: String): Boolean {
            return isValidExpirationYear(value)
        }
    },
    hgt {
        override fun validate(value: String): Boolean {
            return isValidHeight(value)
        }
    },
    hcl {
        override fun validate(value: String): Boolean {
            return isValidHairColor(value)
        }
    },
    ecl {
        override fun validate(value: String): Boolean {
            return isValidEyeColor(value)
        }
    },
    pid {
        override fun validate(value: String): Boolean {
            return isValidPassportId(value)
        }
    };

    abstract fun validate(value: String): Boolean

}

fun checkIntRange(value:String, range: IntRange) : Boolean {
    return try {
        value.toInt() in range
    } catch (e: Exception) {
        false
    }
}

fun isValidBirthYear(value: String) = checkIntRange(value, 1920..2002)

fun isValidIssueYear(value: String) = checkIntRange(value,2010..2020)

fun isValidExpirationYear(value: String) = checkIntRange(value, 2020..2030)

fun isValidHeight(value: String): Boolean {

    if (value.matches(Regex("""^\d{2}in$"""))) {
        val numeric = value.substring(0, value.length - 2)
        return numeric.toInt() in 59..76
    }
    if (value.matches(Regex("""^\d{3}cm$"""))) {
        val numeric = value.substring(0, value.length - 2)
        return numeric.toInt() in 150..193
    }
    return false;

}

fun isValidHairColor(value: String): Boolean {
    return value.matches(Regex("""^#[0-9a-f]{6}$"""))
}

fun isValidEyeColor(value: String): Boolean {
    return value.matches(Regex("""^(amb|blu|brn|gry|grn|hzl|oth)$"""))
}

fun isValidPassportId(value: String): Boolean {
    return value.matches(Regex("""^\d{9}${'$'}"""))
}


fun getInputAsString() = File("src/main/resources/y2020/d4/input.txt")
    .readText()

fun getPassportsFromOneLineStrings(input: String): List<String> {
    return input.split("\n\n")
        .map { it -> it.replace("\n", " ") }
}

fun main() {
    val input = getInputAsString();
    val passportLines = getPassportsFromOneLineStrings(input)
    val passports = getPassportsFromLines(passportLines)
    val countValidPassports = countValidPassports(passports, ::isValid)

    println(countValidPassports)

}

data class Passport(val map: Map<String, Any?>) {
    val byr: Int by map
    val iyr: Int by map
    val eyr: Int by map
    val hgt: String by map
    val hcl: String by map
    val ecl: String by map
    val pid: String by map
    val cid: String by map

    override fun toString(): String = map.toSortedMap().toString()
}

fun getPassportsFromLines(passportLines: List<String>): List<Passport> {
    return passportLines.map {
        parsePassport(it)
    }
}

fun countValidPassports(
    passports: List<Passport>,
    validationFun: ((passport: Passport) -> Boolean)
): Int {
    return passports.filter { validationFun(it) }.size
}

fun isValid(passport: Passport): Boolean {
    MandatoryFields.values().forEach {
        if (!passport.map.keys.contains(it.name) || passport.map[it.name] == null) {
            return false
        }
    }
    return true
}

fun parsePassport(passportLine: String): Passport {
    return Passport(passportLine
        .split(" ")
        .fold(mutableMapOf()) { acc, value ->
            val tokens = value.split(":")
            if (tokens.size > 1) {
                acc[tokens[0]] = tokens[1]
            }
            acc
        }
    )
}
