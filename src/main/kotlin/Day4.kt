import java.io.File

fun main() {
    val ranges = File("src/main/resources/day4.txt").readLines()
        .map { it.split(",")
            .map { r ->
                val range = r.split("-").map{i -> i.toInt()}
                range[0].rangeTo(range[1])
            }
        }

    part1(rangeTuples = ranges)
    part2(rangeTuples = ranges)
}

fun part1(rangeTuples: List<List<IntRange>>){
    val result = rangeTuples.count {
        it[0].contains(it[1]) || it[1].contains(it[0])
    }
    println("Part 1: $result")
}

fun part2(rangeTuples: List<List<IntRange>>){
    val result = rangeTuples.count {
        it[0].overlaps(it[1])
    }
    println("Part 1: $result")
}

fun IntRange.contains(other: IntRange): Boolean{
    return this.first <= other.first && this.last >= other.last;
}

fun IntRange.overlaps(other: IntRange): Boolean {
    return this.contains(other) || other.contains(this) || this.intersect(other).isNotEmpty()
}
