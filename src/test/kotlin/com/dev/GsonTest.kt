package com.dev

import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class GsonTest {

    private val deserializerClient = Gson()

    @Test
    fun `should deserialize using default values`() {
        // Given
        val json = jsonWithAllRequiredKeys()

        // When
        val result = deserializerClient.fromJson(json, Content::class.java)

        // Then
        assertEquals("Required string value", result.requiredString)
        assertEquals(42, result.requiredInt)
        assertEquals(true, result.booleanWithDefaultValue) // !!
    }

    @Test
    fun `should fail when deserializing with missing required property (null-safety)`() {
        // Given
        val json = jsonWithMissingRequiredKey()

        // When/Then
        assertFailsWith<JsonSyntaxException>("Deserialization should have failed") {
            val result = deserializerClient.fromJson(json, Content::class.java)
            println("====> Deserialized result: $result") // !!
        }
    }

    @Test
    fun `should fail when deserializing a decimal value into an integer property`() {
        // Given
        val json = jsonWithIntAsDecimal()

        // When / Then
        assertFailsWith<JsonSyntaxException>("Deserialization should have failed") {
            val result = deserializerClient.fromJson(json, Content::class.java)
            println("====> Deserialized result: $result") // !!
        }
    }
}