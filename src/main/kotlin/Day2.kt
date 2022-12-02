import java.io.File

val symbols: List<String> = listOf("Rock", "Paper", "Scissors")

fun main() {
    val rounds = File("src/main/resources/day2.txt").readLines()
        .map {
            Pair(
                it.split(" ")[0],
                it.split(" ")[1]
            )
        }

    part1(rounds = rounds)
    part2(rounds = rounds)
}

private fun part1(rounds: List<Pair<String, String>>) {

    val result1 = rounds
        .map {
            Pair(
                getSymbol(it.first),
                getSymbol(it.second)
            )
        }
        .sumOf { calculateResult(it) }
    println("Part 1: $result1")

}

fun part2(rounds: List<Pair<String, String>>) {
    val result2 = rounds.map {
        val opponent = getSymbol(it.first)
        val increment = when (it.second) {
            "X" -> -1
            "Y" -> 0
            "Z" -> 1
            else -> error("Kaputt")
        }
        Pair(
            opponent,
            symbols[nextIndex(symbols.indexOf(opponent), increment)]
        )
    }.sumOf { calculateResult(it) }
    println("Part 2: $result2")
}

private fun calculateResult(round: Pair<String, String>): Int {
    val opponent = round.first
    val me = round.second

    val result = when {
        me.winsAgainst(opponent) -> 6
        opponent.winsAgainst(me) -> 0
        else -> 3
    }

    return result + symbols.indexOf(me) + 1
}

private fun String.winsAgainst(other: String): Boolean {
    val newIndex = nextIndex(symbols.indexOf(this), -1)
    return symbols[newIndex] == other
}

private fun nextIndex(currentIndex: Int, increment: Int): Int {
    val newIndex = currentIndex + increment
    return when {
        newIndex < 0 -> symbols.lastIndex
        newIndex > symbols.lastIndex -> 0
        else -> newIndex
    }
}

private fun getSymbol(letter: String): String = when (letter) {
    "A", "X" -> symbols[0]
    "B", "Y" -> symbols[1]
    "C", "Z" -> symbols[2]
    else -> error("Kaputt")
}
