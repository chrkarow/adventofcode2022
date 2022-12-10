import java.io.File

fun main() {
    val lines = File("src/main/resources/day6.txt").readLines()

    println("Part 1: ${parseMarkers(characters = lines[0], distinctLength = 4)}")
    println("Part 2: ${parseMarkers(characters = lines[0], distinctLength = 14)}")
}

fun parseMarkers(characters: String, distinctLength: Int) =
    characters.windowed(distinctLength)
        .indexOfFirst { it.toCharArray().distinct().size == distinctLength } + distinctLength
