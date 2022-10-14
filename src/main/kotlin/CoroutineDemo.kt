//import kotlinx.coroutines.*
//
//object obj {
//    const val b: Boolean = false
//
//    fun run() {
//        if (true) {
//            println("run block")
//        }
//
//        if (b) {
//            println("death block")
//        }
//    }
//}
//
//fun coroutineDemo() {
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
//}
