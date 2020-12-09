package y2020.d5

fun main() {
    val input = getInput()
    val seats = getSeats(input)

    val airplaneMap: Array<Array<Int>> = seats.fold(Array(128) { Array(8) { 0 } }, { acc, seat ->
        acc[seat.row][seat.seat] = seat.seatId
        acc
    })

    val firstValidFrontRow = airplaneMap
        .withIndex()
        .indexOfLast {
            it.index < 63
                    && it.value.count { it == 0 } == 8
        } + 1
    val firstValidBackRow = airplaneMap
        // .slice(64..127)
        .withIndex()
        .indexOfFirst {
            it.index > 64
                    && it.value.count { it == 0 } == 8
        } - 1

    val myRow = airplaneMap
        .slice(firstValidFrontRow..firstValidBackRow)
        .first { it.count { it == 0 } == 1 }
    val mySeatIndex = myRow.indexOf(0)
    val mySeatId = if (mySeatIndex < 7) {
        myRow[mySeatIndex + 1] - 1
    } else {
        myRow[mySeatIndex - 1] + 1
    }
    println(mySeatId)


}