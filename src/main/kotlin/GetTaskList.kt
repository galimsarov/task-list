const val SEPARATOR = "+----+------------+-------+---+---+--------------------------------------------+"

fun addTask() {
    val priority = getPriority()
    val date = getDate()
    val time = getTime(date)
    val taskLines = getTaskLines()
    if (taskLines.isNotEmpty()) {
        TASK_LIST.add(Task(priority, date[0], date[1], date[2], time[0], time[1], taskLines))
    }
}

fun printTaskList() {
    if (TASK_LIST.isEmpty()) {
        println("No tasks have been input")
    } else {
        printHeader()
        for (i in TASK_LIST.indices) {
            TASK_LIST[i].print(i)
            println(SEPARATOR)
        }
    }
}

private fun printHeader() {
    println(SEPARATOR)
    println("| N  |    Date    | Time  | P | D |                   Task                     |")
    println(SEPARATOR)
}