fun deleteOrEditTask(isEdit: Boolean = false) {
    if (TASK_LIST.size > 0) {
        while (true) {
            println("Input the task number (1-${TASK_LIST.lastIndex + 1}):")
            try {
                val taskNumber = readln().toInt()
                if (taskNumber - 1 in TASK_LIST.indices) {
                    if (isEdit) {
                        editTask(taskNumber - 1)
                        println("The task is changed")
                    } else {
                        TASK_LIST.removeAt(taskNumber - 1)
                        println("The task is deleted")
                    }
                    break
                } else {
                    println("Invalid task number")
                }
            } catch (_: Exception) {
                println("Invalid task number")
            }
        }
    }
}

private fun editTask(taskIndex: Int) {
    while (true) {
        println("Input a field to edit (priority, date, time, task):")
        when (readln().lowercase()) {
            "priority" -> {
                TASK_LIST[taskIndex].priority = getPriority()
                break
            }
            "date" -> {
                val date = getDate()
                TASK_LIST[taskIndex].year = date[0]
                TASK_LIST[taskIndex].month = date[1]
                TASK_LIST[taskIndex].day = date[2]
                break
            }
            "time" -> {
                val time = getTime(
                    listOf(
                        TASK_LIST[taskIndex].year, TASK_LIST[taskIndex].month, TASK_LIST[taskIndex].day
                    )
                )
                TASK_LIST[taskIndex].hour = time[0]
                TASK_LIST[taskIndex].minute = time[1]
                break
            }
            "task" -> {
                TASK_LIST[taskIndex].taskLines = getTaskLines()
                break
            }
            else -> println("Invalid field")
        }
    }
}