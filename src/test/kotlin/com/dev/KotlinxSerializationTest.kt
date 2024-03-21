package com.dev

import kotlinx.serialization.SerializationException
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class KotlinxSerializationTest {

    private val deserializerClient = Json

    @Test
    fun `should deserialize using default values`() {
        // Given
        val json = jsonWithAllRequiredKeys()

        // When
        val result = deserializerClient.decodeFromString<Content>(json)

        // Then
        assertEquals("Required string value", result.requiredString)
        assertEquals(42, result.requiredInt)
        assertEquals(true, result.booleanWithDefaultValue) // Default value is used
    }

    @Test
    fun `should fail when deserializing with missing required property (null-safety)`() {
        // Given
        val json = jsonWithMissingRequiredKey()

        // When/Then
        assertFailsWith<SerializationException> {
            val result = deserializerClient.decodeFromString<Content>(json)
            println("====> Deserialized result: $result")
        }
    }

    @Test
    fun `should fail when deserializing a decimal value into an integer property`() {
        // Given
        val json = jsonWithIntAsDecimal()

        // When / Then
        assertFailsWith<SerializationException> {
            val result = deserializerClient.decodeFromString<Content>(json)
            println("====> Deserialized result: $result")
        }
    }
}