package y2020.d4

fun main() {
    val input = getInputAsString();
    val passportLines = getPassportsFromOneLineStrings(input)
    val passports = getPassportsFromLines(passportLines)
    val countValidPassports = countValidPassports(passports, ::isValidPart2)

    println(countValidPassports)

}

fun isValidPart2(passport: Passport): Boolean {
    MandatoryFields.values().forEach {
        if (!passport.map.keys.contains(it.name)
            || passport.map[it.name] == null
            || !it.validate(passport.map[it.name].toString() ?: error("Cannot validate null value"))) {
                println("Passport $passport is invalid because of [${it.name}] which is [${passport.map[it.name]}]")
            return false
        }
    }
    return true
}

