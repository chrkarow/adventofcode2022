import java.io.File
import java.util.*

data class Instruction(val amount: Int, val fromIndex: Int, val toIndex: Int)

fun main() {
    val lines = File("src/main/resources/day5.txt").readLines()
    val instructions = parseInstructions(strings = lines.drop(10))

    part1(stacks = createStacks(strings = lines.take(9)), instructions = instructions)
    part2(stacks = createStacks(strings = lines.take(9)), instructions = instructions)
}

fun part1(stacks: List<Stack<String>>, instructions: List<Instruction>) {
    instructions.forEach { i ->
        repeat(i.amount) { stacks[i.toIndex].push(stacks[i.fromIndex].pop())}
    }

    val result = stacks.joinToString(separator = "") { it.peek() }
    println("Part1: $result")
}

fun part2(stacks: List<Stack<String>>, instructions: List<Instruction>) {
    instructions.forEach { i ->
        val intermediateStack = Stack<String>()
        repeat(i.amount) { intermediateStack.push(stacks[i.fromIndex].pop())}
        repeat(i.amount) { stacks[i.toIndex].push(intermediateStack.pop())}
    }

    val result = stacks.joinToString(separator = "") { it.peek() }
    println("Part2: $result")
}

fun createStacks(strings: List<String>): List<Stack<String>> {
    val result = mutableListOf<Stack<String>>()

    var column = 1
    for (i in 1..9) {
        val stack = Stack<String>()
        for (l in 7 downTo 0) {
            if (column >= strings[l].length) continue
            val elem = strings[l].substring(column, column + 1)
            if (elem.isNotBlank()) stack.push(elem)
        }
        column += 4
        result.add(stack)
    }

    return result
}

fun parseInstructions(strings: List<String>) =
    strings.map {
        it.replace("move ", "")
            .replace("from ", "")
            .replace("to ", "")
            .split(" ")
            .let { l ->
                Instruction(
                    amount = l[0].toInt(),
                    fromIndex = l[1].toInt() - 1,
                    toIndex = l[2].toInt() - 1
                )
            }
    }



