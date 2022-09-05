import kotlinx.datetime.*

data class Task(
    var priority: String,
    var year: Int,
    var month: Int,
    var day: Int,
    var hour: Int,
    var minute: Int,
    var taskLines: List<String>,
) {
    fun print(i: Int) {
        val currentDate = Clock.System.now().toLocalDateTime(TimeZone.of("UTC+0")).date
        val numberOfDays = currentDate.daysUntil(LocalDate(year, month, day))
        val result = "| ${
            if (i + 1 < 10) {
                "${i + 1}  "
            } else {
                "${i + 1} "
            }
        }| ${year}-${correctNumber(month)}-${correctNumber(day)} | ${correctNumber(hour)}:${correctNumber(minute)} | " +
            "${getPriorityColor(priority)} | ${getDueTagColor(numberOfDays)} |${getStringTask()}|"
        println(result)
    }

    private fun getPriorityColor(priority: String) = when (priority) {
        "C" -> "\u001B[101m \u001B[0m"
        "H" -> "\u001B[103m \u001B[0m"
        "N" -> "\u001B[102m \u001B[0m"
        else -> "\u001B[104m \u001B[0m"
    }

    private fun getDueTagColor(numberOfDays: Int) = when {
        numberOfDays > 0 -> "\u001B[102m \u001B[0m"
        numberOfDays < 0 -> "\u001B[101m \u001B[0m"
        else -> "\u001B[103m \u001B[0m"
    }

    private fun correctNumber(number: Int) =
        if (number < 10) {
            "0$number"
        } else {
            number.toString()
        }

    private fun getStringTask(): String {
        var result = ""
        for (i in taskLines.indices) {
            if (i != 0) {
                result += "|\n|    |            |       |   |   |"
            }
            result += getSubTask(taskLines[i])
        }
        return result
    }

    private fun getSubTask(task: String): String {
        var result = ""
        val taskLineLength = 44
        if (task.length <= taskLineLength) {
            result += task + " ".repeat(taskLineLength - task.length)
        } else {
            var tmp = task
            while (tmp.length > taskLineLength) {
                result += tmp.substring(0, taskLineLength) + "|\n|    |            |       |   |   |"
                tmp = tmp.substring(taskLineLength)
            }
            result += tmp + " ".repeat(taskLineLength - tmp.length)
        }
        return result
    }
}
