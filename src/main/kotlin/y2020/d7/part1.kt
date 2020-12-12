package y2020.d7

import java.io.File

fun getInput() = File("src/main/resources/y2020/d7/input.txt").useLines { it.toList() }

fun main() {
    val input = getInput()
    val bags = buildBagsFromRules(input)
    val myBag = "shiny gold"

    val containerBags = findContainerBags(myBag, bags) // first level containers

    println(containerBags.size)
}

fun findContainerBags(myBag: String, bags: Map<String, Map<String, Int>>): Set<String> {
    var directContainerBags = bags.keys.fold(mutableSetOf<String>()) { acc, value ->
        if ((bags[value] ?: error("Invalid value $value")).containsKey(myBag)) {
            acc.add(value)
            acc
        } else {
            acc
        }
    }
    return if (directContainerBags.isEmpty()) {
        emptySet()
    } else {
        directContainerBags.forEach {
            directContainerBags = directContainerBags.union(findContainerBags(it, bags)) as MutableSet<String>
        }
        directContainerBags
    }
}

/* I would like to represent a graph using an adjacency matrix like this:
                            "light red" "bright white" "muted yellow" "dark orange" "shiny gold"  "dark olive" "vibrant plum" "faded blue" "dotted black"
        "light red"                         1               2
      "dark orange"                         3               4
     "bright white"                                                                     1
     "muted yellow"                                                                     2                                           9
       "shiny gold"                                                                                     1           2
       "dark olive"                                                                                                                 3               4
     "vibrant plum"                                                                                                                 5               6
       "faded blue"
     "dotted black"

which in turn is a map like:
    mapOf(
        "light red" to mapOf("bright white" to 1, "muted yellow" to 2),
        "dark orange" to mapOf("bright white" to 3, "muted yellow" to 2),
        "bright white" to mapOf("shiny gold" to 1),
        "muted yellow" to mapOf("shiny gold" to 2, "faded blue" to 9),
        "shiny gold" to mapOf("dark olive" to 1, "vibrant plum" to 2),
        "dark olive" to mapOf("faded blue" to 3, "dotted black" to 4),
        "vibrant plum" to mapOf("faded blue" to 5, "dotted black" to 6),
        "faded blue" to emptyMap(),
        "dotted black" to emptyMap()
    )


*/
fun buildBagsFromRules(input: List<String>): Map<String, Map<String, Int>> {
    return input.fold(mutableMapOf()) { acc, value ->
        val bag = parseRule(value)
        acc[bag.first] = bag.second
        acc
    }
}

fun parseRule(value: String): Pair<String, Map<String, Int>> {
    // val regexPattern = """(\w+( \w+)*) bags contain (((\d) (\w+( \w+)*) bag(s?))(, (\d) (\w+( \w+)*) bag(s?))*|no other bags)\."""
    // using a single regexp can be hard to manage here... i'm going to use some tokenization instead
    val split = value.indexOf(" bags contain ")
    val color = value.slice(0 until split)

    val containedBags = if (value.contains("no other bags")) {
        emptyMap<String, Int>()
    } else {
        val tokens = value.slice(split + 14..value.indexOf(".")).split(",")
        tokens.fold(mutableMapOf()) { acc, value ->
            val words = value.trim().split(" ")
            val quantity = words[0].toInt()
            val color = "${words[1]} ${words[2]}"
            acc[color] = quantity
            acc
        }

    }
    return Pair(color, containedBags)
}

