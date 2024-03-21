package com.dev;

import java.nio.charset.Charset

fun jsonWithAllRequiredKeys(): String = getResourceContent("all_required_keys_are_present.json")
fun jsonWithMissingRequiredKey(): String = getResourceContent("missing_required_string_property.json")
fun jsonWithIntAsDecimal(): String = getResourceContent("missing_required_string_property.json")

private fun getResourceContent(resourcePath: String, charset: Charset = Charsets.UTF_8): String =
    object {}.javaClass.classLoader.getResource(resourcePath)!!.readText(charset)