import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import java.lang.reflect.Type

annotation class KKStringValue
annotation class KKIntValue

interface ValueClass<T> {
}

@JvmInline
@KKStringValue
value class SkuId(val value: String = "") : ValueClass<String> {
}

@JvmInline
@KKIntValue
value class ProductId(val value: Int = 123) : ValueClass<Int> {
}

data class Product(
    val sku: SkuId,
    val productId: ProductId
)


fun valueClassDemo() {
    val skuId = SkuId("this is sku")
    val productId = ProductId(66666)


    val gson = GsonSingleton.singleton
    println("skuId $skuId, to json " + gson.toJson(skuId))
    println("productId $skuId, to json " + gson.toJson(productId))

    val product = Product(SkuId("this is sku"), ProductId(66666))
    println(
        "product $product, to json ${gson.toJson(product)}," +
                " from json ${gson.fromJson(gson.toJson(product), Product::class.java)}"
    )

}

object GsonSingleton {
    val singleton: Gson by lazy {
        return@lazy GsonBuilder()
            .registerTypeHierarchyAdapter(
                ValueClass::class.java,
                object : JsonSerializer<ValueClass<Any>>, JsonDeserializer<ValueClass<Any>> {
                    override fun serialize(
                        src: ValueClass<Any>?,
                        typeOfSrc: Type?,
                        context: JsonSerializationContext
                    ): JsonElement {
                        val annotationName = (typeOfSrc as Class<Any>).annotations
                            .first {
                                it.annotationClass.simpleName?.startsWith("KK") == true
                            }.annotationClass.simpleName

                        return when (annotationName) {
                            KKStringValue::class.simpleName -> {
                                val firstField = typeOfSrc?.declaredFields?.first()?.apply {
                                    isAccessible = true
                                }
                                context.serialize(firstField?.get(src))
                            }

                            KKIntValue::class.simpleName -> {
                                val firstField = typeOfSrc?.declaredFields?.first()?.apply {
                                    isAccessible = true
                                }
                                context.serialize(firstField?.get(src))
                            }

                            else -> {
                                throw IllegalArgumentException("Unknown annotation")
                            }
                        }
                    }

                    override fun deserialize(
                        json: JsonElement?,
                        typeOfT: Type?,
                        context: JsonDeserializationContext?
                    ): ValueClass<Any> {
                        val annotationName = (typeOfT as Class<Any>).annotations
                            .first {
                                it.annotationClass.simpleName?.startsWith("KK") == true
                            }.annotationClass.simpleName

                        val firstArg = when (annotationName) {
                            KKStringValue::class.simpleName -> {
                                context?.deserialize<String>(json, String::class.java) ?: ""
                            }

                            KKIntValue::class.simpleName -> {
                                context?.deserialize<Int>(json, Int::class.java) ?: 0
                            }

                            else -> {
                                throw IllegalArgumentException("Unknown annotation")
                            }
                        }
                        return typeOfT.declaredConstructors.first()!!
                            .newInstance(firstArg) as ValueClass<Any>
                    }
                })
            .create()
    }
}