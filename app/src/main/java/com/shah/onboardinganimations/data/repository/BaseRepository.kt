package com.shah.onboardinganimations.data.repository

import com.shah.onboardinganimations.utils.ResponseResource
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.statement.bodyAsText
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Created by Monil on 01/08/25.
 */

abstract class BaseRepository {

    suspend fun <T> safeApiCall(apiCall: suspend () -> T): ResponseResource<T> {
        return withContext(Dispatchers.IO) {
            try {
                val result = apiCall()
                ResponseResource.Success(result)
            } catch (e: RedirectResponseException) {
                // 3xx
                logError("Redirect Error: ${e.response.status.description}", e)
                ResponseResource.Failure(false, "Redirect error: ${e.response.status.value}")
            } catch (e: ClientRequestException) {
                // 4xx
                val errorMessage = parseKtorError(e)
                logError("Client Error (4xx): $errorMessage", e)
                ResponseResource.Failure(false, errorMessage)
            } catch (e: ServerResponseException) {
                // 5xx
                val errorMessage = parseKtorError(e)
                logError("Server Error (5xx): $errorMessage", e)
                ResponseResource.Failure(false, errorMessage)
            } catch (e: java.net.ConnectException) {
                logError("Connection Error: ${e.localizedMessage}", e)
                ResponseResource.Failure(true, "No Internet connection.")
            } catch (e: java.net.SocketTimeoutException) {
                logError("Timeout: ${e.localizedMessage}", e)
                ResponseResource.Failure(true, "Server took too long to respond.")
            } catch (e: Exception) {
                logError("Unexpected Error: ${e.localizedMessage}", e)
                ResponseResource.Failure(true, e.localizedMessage ?: "Unknown error")
            }
        }
    }

    open suspend fun parseKtorError(exception: ResponseException): String {
        return try {
            val errorBody = exception.response.bodyAsText()
            errorBody.takeIf { it.isNotBlank() } ?: exception.message ?: "Unknown server error"
        } catch (e: Exception) {
            "Server returned an error (HTTP ${exception.response.status.value})"
        }
    }

    open fun logError(message: String, throwable: Throwable? = null) {
        println("BaseRepository Error -> $message")
        throwable?.printStackTrace()
    }
}
