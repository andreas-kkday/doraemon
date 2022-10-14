import com.google.gson.*
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type


data class GenericData<T>(
    val data: T
)

fun gsonGenericDemo() {
    val gson = GsonBuilder().create()
    val stringList = listOf("Android", "iOS")
    println(">>> string list " + gson.toJson(stringList))

    println(">>> decode " + decode<List<String>>(gson, gson.toJson(stringList)))
    println(">>> decode " + decode<Array<String>>(gson, gson.toJson(stringList)).toList())

    val intData = GenericData<Int>(1)   //{"data":1}
    val stringData = GenericData<String>("One") //{"data":"One"}
    println(">>> " + gson.toJson(intData))
    println(">>> " + gson.toJson(stringData))

    println(">>> decode " + decode<GenericData<Int>>(gson, """ {"data":1} """).data::class.java)
    println(">>> decode " + decode<GenericData<String>>(gson, """ {"data":"One"} """).data::class.java)

    val intArrayData: GenericData<List<Int>> = GenericData(listOf(1, 2, 3))
    val stringArrayData: GenericData<List<String>> = GenericData(listOf("One", "Two", "Three"))
    println(">>> " + gson.toJson(intArrayData)) // {"data":[1,2,3]}
    println(">>> " + gson.toJson(stringArrayData))// {"data":["One","Two","Three"]}

    println(">>> decode " + decode<GenericData<List<Int>>>(gson, """ {"data":[1,2,3]} """).data)
    println(">>> decode " + decode<GenericData<List<String>>>(gson, """ {"data":["One","Two","Three"]} """).data)
}

abstract class TypeReference<T> : Comparable<TypeReference<T>> {
    val type: Type =
        (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0]

    override fun compareTo(other: TypeReference<T>) = 0
}

inline fun <reified T> decode(gson: Gson, json: String): T {
    val type = object : TypeReference<T>() {}.type

    return gson.fromJson(json, type)
}

