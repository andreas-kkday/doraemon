//import com.google.gson.*
//import com.google.gson.annotations.SerializedName
//import java.lang.reflect.ParameterizedType
//import java.lang.reflect.Type
//
////import kotlinx.coroutines.*
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
//
//data class D(val a: List<Int>, val b: String?)
//
//@JvmInline
//value class ProductOid2(val oid: String)
//
//@JvmInline
//value class ProductMid2(val mid: String)
//
//
//fun main(args: Array<String>) {
////    val json = """
////        {};
////        {"a":[]};
////        {"a":null}
////    """.trimIndent()
////
////    val jsonList = json.split(";")
////
////    jsonList.forEach {
////        val decode = gson.fromJson<D>(it, D::class.java)
////        try {
////            println(">>> decode $it to $decode, hashCode ${decode.hashCode()}")
////        } catch (e: Exception) {
////            println(">>> hashCode error $e")
////        }
////        try {
////            println(">>> copy instance " + decode.copy(b = "Copy"))
////        } catch (e: Exception) {
////            println(">>> Copy error $e")
////        }
////    }


fun main(args: Array<String>) {
    gsonGenericDemo()
    valueClassDemo()
}
