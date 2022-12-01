import java.io.File

fun main() {
    val sections = File("src/main/resources/day1.txt").readText().split("\n\n")
        .map { section ->
            section
                .split("\n")
                .filter(String::isNotEmpty)
                .map { it.toInt() }
        }

    part1(calories = sections)
    part2(calories = sections)
}

private fun part1(calories: List<List<Int>>) {
    val result1 = calories.maxOf {
        it.sum()
    }
    println("Part 1: $result1")
}

private fun part2(calories: List<List<Int>>) {
    val result2 = calories
        .map { it.sum() }
        .sortedDescending()
        .subList(0, 3)
        .sum()
    println("Part 2: $result2")
}

