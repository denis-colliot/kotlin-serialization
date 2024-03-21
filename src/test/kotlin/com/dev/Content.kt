package com.dev

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Content(
    @SerialName("required_string")
    @SerializedName("required_string")
    val requiredString: String,

    @SerialName("required_int")
    @SerializedName("required_int")
    val requiredInt: Int,

    @SerialName("boolean_with_default_value")
    @SerializedName("boolean_with_default_value")
    val booleanWithDefaultValue: Boolean = true
)