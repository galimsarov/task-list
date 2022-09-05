import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.io.File

const val FILE_NAME = "tasklist.json"

fun saveTaskList() {
    try {
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        val type = Types.newParameterizedType(List::class.java, Task::class.java)
        val taskListAdapter = moshi.adapter<List<Task?>>(type)
        val json = taskListAdapter.toJson(TASK_LIST)

        val jsonFile = File(FILE_NAME)
        jsonFile.writeText(json)
    } catch (_: Exception) {

    }
}

fun getTaskList(): MutableList<Task> {
    try {
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        val type = Types.newParameterizedType(List::class.java, Task::class.java)
        val taskListAdapter = moshi.adapter<List<Task?>>(type)

        val jsonFile = File(FILE_NAME)
        val json = jsonFile.readText()
        val taskList = taskListAdapter.fromJson(json)
        return if (taskList != null) {
            if (taskList.isEmpty()) {
                mutableListOf()
            } else {
                val result = mutableListOf<Task>()
                for (task in taskList) {
                    if (task != null) {
                        result.add(task)
                    }
                }
                result
            }
        } else {
            mutableListOf()
        }
    } catch (_: Exception) {
        return mutableListOf()
    }
}