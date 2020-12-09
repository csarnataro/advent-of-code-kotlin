package y2020.d1

import java.io.File

fun getInput() = File("src/main/resources/y2020/d1/test-input.txt").useLines { it.toList() }

fun main() {
    val input = getInput()
    val result = process(input.map { it.toInt() })
    println(result)
}

fun process(input: List<Int>): Int {
    for (i in input.indices) {
        for (j in i until input.size ) {
            /* PART 1 */
            if (input[i] + input[j] == 2020) {
                return input[i] * input[j]
            }

            /* PART 2 */
//            for (k in j until input.size) {
//                if (input[i] + input[j] + input[k] == 2020) {
//                    return input[i] * input[j] * input[k]
//                }
//            }


        }
    }
    return 0
}

