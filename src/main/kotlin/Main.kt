import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import kotlin.reflect.KFunction

//import kotlinx.coroutines.*

object obj {
    const val b: Boolean = false

    fun run() {
        if (true) {
            println("run block")
        }

        if (b) {
            println("death block")
        }
    }
}


enum class GoDateSettingType(val type: Int, val code: String) {
    @SerializedName("NONE")
    NONONONONO(-1, "GODATE_SETTING_TYPE_CODE_NONE"),
    SINGLE(1, "GODATE_SETTING_TYPE_CODE_SINGLE"),
}

data class D(val a: List<Int>, val b: String?)

fun main(args: Array<String>) {
    val gson = Gson()

    println(gson.toJson(GoDateSettingType.NONONONONO))
//    val json = """
//        {};
//        {"a":[]};
//        {"a":null}
//    """.trimIndent()
//
//    val jsonList = json.split(";")
//
//    jsonList.forEach {
//        val decode = gson.fromJson<D>(it, D::class.java)
//        try {
//            println(">>> decode $it to $decode, hashCode ${decode.hashCode()}")
//        } catch (e: Exception) {
//            println(">>> hashCode error $e")
//        }
//        try {
//            println(">>> copy instance " + decode.copy(b = "Copy"))
//        } catch (e: Exception) {
//            println(">>> Copy error $e")
//        }
//    }

//    obj.run()

//    println("Hello World!")
//
//    val s1 = "string"
//    val s2 = "string"
//    s1 === s2
//
//    // Try adding program arguments via Run/Debug configuration.
//    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
//    println("Program arguments: ${args.joinToString()}")
//    GlobalScope.launch {
//        val user1 = async {
//            for(i in 0 until 10) {
//                print("a$i ${Thread.currentThread()}")
//                sleep(100) //有沒有 sleep 會有不同結果
//
//                //https://mohak1712.medium.com/kotlin-coroutines-thread-sleep-vs-delay-63171fe8a24
//                //如果照這裡說, sleep 跟 delay 應該會有不同結果才對, sleep 會卡住這個 coroutine, delay 會 suspend 然後可以切換到其他 coroutine
//            }
//        }
//        val user2 = async {
//            for(i in 0 until 10) {
//                print("b$i ${Thread.currentThread()}")
//                sleep(100) //有沒有 sleep 會有不同結果
//            }
//        }
//
//        user1.await()
//        user2.await()
//
//        println("Done")
//    }
//    Thread.sleep(200 * 1000)


}
