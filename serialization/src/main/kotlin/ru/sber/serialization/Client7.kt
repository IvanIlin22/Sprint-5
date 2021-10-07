package ru.sber.serialization

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.core.TreeNode
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.deser.std.StdDeserializer

@JsonDeserialize(using = CustomClassDeserializer::class)
data class Client7(
    val firstName: String,
    val lastName: String,
    val middleName: String,
)

class CustomClassDeserializer : StdDeserializer<Client7>(Client7::class.java) {
    override fun deserialize(p0: JsonParser, p1: DeserializationContext?): Client7 {
        val tn: TreeNode = p0.readValueAsTree()
        val client = tn.get("client").toString().replace("\"", "").split(" ")

        return Client7(client[1], client[0], client[2])
    }
}
