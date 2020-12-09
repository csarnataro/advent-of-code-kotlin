package y2020.d5

import java.io.File

fun getInput() = File("src/main/resources/y2020/d5/input.txt").useLines { it.toList() }

fun main() {
    val input = getInput()
    val seats = getSeats(input)
    val higherSeatId = getHigherSeatId(seats)

    println(higherSeatId)
}

fun getHigherSeatId(seats: List<Seat>): Int {
    val maxSeat = seats.maxByOrNull { it.seatId }!!
    return maxSeat.seatId
}

fun getSeats(lines: List<String>): List<Seat> {
    return lines.map {
        getSeat(it)
    }
}

fun getSeat(ticket: String): Seat {
    val rowLetters = ticket.slice(0 until 7)
    val seatLetters = ticket.slice(7 until 10)

    val row = getRowFromLetters(rowLetters)
    val seat = getSeatFromLetters(seatLetters)

    val seatId = row * 8 + seat


    return Seat(row, seat, seatId)
}

fun getSeatFromLetters(seatLetters: String): Int {
    var currentSeatRange = 0..7

    for (letter in seatLetters) {
        when (letter) {
            'R' -> currentSeatRange = (((currentSeatRange.last + currentSeatRange.first + 1) / 2))..currentSeatRange.last
            'L' -> currentSeatRange = currentSeatRange.first..(((currentSeatRange.last + currentSeatRange.first + 1) / 2))
        }
    }
    return currentSeatRange.first

}

fun getRowFromLetters(rowLetters: String): Int {
    var currentRowRange = 0..127

    for (letter in rowLetters) {
        when (letter) {
            'B' -> currentRowRange = (((currentRowRange.last + currentRowRange.first + 1) / 2)..currentRowRange.last)
            'F' -> currentRowRange = currentRowRange.first..(((currentRowRange.last + currentRowRange.first + 1) / 2))
        }
    }
    return currentRowRange.first

}

data class Seat(val row: Int, val seat: Int, val seatId: Int)