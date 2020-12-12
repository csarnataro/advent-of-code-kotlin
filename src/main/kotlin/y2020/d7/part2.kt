package y2020.d7

fun main() {
    val input = getInput()
    val bags = buildBagsFromRules(input)
    val myBag = "shiny gold"

    val containedBags = findContainedBags(myBag, bags) // first level containers

    println(containedBags)
}

fun findContainedBags(myBag: String, bags: Map<String, Map<String, Int>>): Int {
    var directContainedBags = bags[myBag]!!

    return directContainedBags.keys.fold(0) { acc, value ->
        val directlyContainedBagSize = directContainedBags[value]!!
        val innerBagsSize = findContainedBags(value, bags)
        acc + directlyContainedBagSize + (directlyContainedBagSize * innerBagsSize)
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

