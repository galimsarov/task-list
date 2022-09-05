val TASK_LIST = getTaskList()

fun main() {
    while (true) {
        println("Input an action (add, print, edit, delete, end):")
        when (readln().lowercase()) {
            "add" -> addTask()
            "print" -> printTaskList()
            "edit" -> {
                printTaskList()
                deleteOrEditTask(true)
            }
            "delete" -> {
                printTaskList()
                deleteOrEditTask()
            }
            "end" -> {
                println("Tasklist exiting!")
                saveTaskList()
                break
            }
            else -> println("The input action is invalid")
        }
    }
}