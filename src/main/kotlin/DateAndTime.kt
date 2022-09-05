import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime

fun getPriority(): String {
    val priority: String
    while (true) {
        println("Input the task priority (C, H, N, L):")
        when (val priorityInput = readln().uppercase()) {
            "C", "H", "N", "L" -> {
                priority = priorityInput
                break
            }
        }
    }
    return priority
}

fun getDate(): List<Int> {
    val result = mutableListOf<Int>()
    while (true) {
        println("Input the date (yyyy-mm-dd):")
        try {
            val array = readln().split("-").map { it.toInt() }
            LocalDate(array[0], array[1], array[2])
            array.forEach { result.add(it) }
            break
        } catch (_: Exception) {
            println("The input date is invalid")
        }
    }
    return result
}

fun getTime(date: List<Int>): List<Int> {
    val result = mutableListOf<Int>()
    while (true) {
        println("Input the time (hh:mm):")
        try {
            val input = readln()
            val array = input.split(":").map { it.toInt() }
            LocalDateTime(date[0], date[1], date[2], array[0], array[1])
            array.forEach { result.add(it) }
            break
        } catch (_: Exception) {
            println("The input time is invalid")
        }
    }
    return result
}

fun getTaskLines(): List<String> {
    println("Input a new task (enter a blank line to end):")
    val result = mutableListOf<String>()
    while (true) {
        val line = readln().trim()
        if (line.isBlank()) {
            break
        } else {
            result.add(line)
        }
    }
    return if (result.isEmpty()) {
        println("The task is blank")
        listOf()
    } else {
        result
    }
}