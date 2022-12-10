import java.io.File

const val ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"

fun main() {
    val rucksacks = File("src/main/resources/day3.txt").readLines()
    val groupedRucksacks = rucksacks.chunked(3)

    part1(rucksacks = rucksacks)
    part2(groupedRucksacks = groupedRucksacks)
}

fun part1(rucksacks: List<String>) {

    val result = rucksacks.sumOf { rucksack ->
        val halfs = rucksack.chunked(rucksack.length / 2)
        halfs[0].toSet().intersect(halfs[1].toSet()).first().let { c -> ALPHABET.indexOf(c) + 1 }
    }
    println("Part 1: $result")

}

fun part2(groupedRucksacks: List<List<String>>) {
    val result = groupedRucksacks.sumOf {
        it[0].toSet().intersect(it[1].toSet()).intersect(it[2].toSet()).first().let { c -> ALPHABET.indexOf(c) + 1 }
    }
    println("Part 2: $result")
}
