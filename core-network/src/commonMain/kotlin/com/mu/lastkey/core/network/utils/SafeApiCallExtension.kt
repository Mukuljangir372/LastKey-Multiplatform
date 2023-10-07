package com.mu.lastkey.core.network.utils

import com.mu.lastkey.core.utils.model.ResultWrapper
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.utils.io.errors.IOException
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.intOrNull
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

suspend inline fun <reified T> safeApiCall(
    json: Json,
    call: () -> HttpResponse,
    transformResponse: (JsonObject) -> JsonObject
): ResultWrapper<T> {
    return try {
        val response = call()
        val body: String = response.body()

        val jsonObject: JsonObject = transformResponse(json.decodeFromString(body))
        val code = jsonObject["code"]?.jsonPrimitive?.intOrNull

        if (code !in 200..205) {
            ResultWrapper.Failure(
                data = json.decodeJsonOrNull(jsonObject.toString()),
                throwable = null,
                message = jsonObject.getMessage()
            )
        } else {
            ResultWrapper.Success(
                data = json.decodeFromString(jsonObject.toString()),
                message = jsonObject.getMessage()
            )
        }
    } catch (e: IOException) {
        ResultWrapper.Failure(
            throwable = e,
            message = "Network connection error: ${e.message}"
        )
    } catch (e: Exception) {
        ResultWrapper.Failure(
            throwable = e,
            message = e.message.orEmpty()
        )
    }
}

inline fun <reified T> Json.decodeJsonOrNull(json: String): T? {
    return try {
        decodeFromString(json)
    } catch (e: IllegalArgumentException) {
        null
    } catch (e: SerializationException) {
        null
    }
}

fun JsonObject.getMessage(): String {
    val messageKey = "message"
    val dataKey = "data"
    val json = this

    val messagePrimitive = if (json[messageKey] is JsonPrimitive) {
        json[messageKey]?.jsonPrimitive
    } else {
        null
    }

    val messageObjectPrimitive =
        if (json[messageKey] is JsonObject && json[messageKey]?.jsonObject?.get(messageKey) is JsonPrimitive) {
            json[messageKey]?.jsonObject?.get(messageKey)?.jsonPrimitive
        } else {
            null
        }

    val dataMessage =
        if (json[dataKey] is JsonObject && json[dataKey]?.jsonObject?.get(messageKey) is JsonPrimitive) {
            json[dataKey]?.jsonObject?.get(messageKey)?.jsonPrimitive
        } else {
            null
        }

    return if (messagePrimitive?.isString == true &&
        messagePrimitive.content.isNotBlank()
    ) {
        messagePrimitive.content
    } else if (messageObjectPrimitive?.isString == true &&
        messageObjectPrimitive.content.isNotBlank()
    ) {
        messageObjectPrimitive.content
    } else if (dataMessage?.isString == true &&
        dataMessage.content.isNotBlank()
    ) {
        dataMessage.content
    } else {
        ""
    }
}
