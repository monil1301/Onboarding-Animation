package com.shah.onboardinganimations.utils

/**
 * Created by Monil on 01/08/25.
 */

sealed class ResponseResource<out T> {

    data class Success<out T>(
        val data: T
    ) : ResponseResource<T>()

    data class Failure(
        val isNetworkError: Boolean = false,
        val errorMessage: String = "An unknown error occurred",
        val statusCode: Int? = null
    ) : ResponseResource<Nothing>()

    object Loading: ResponseResource<Nothing>()
    object Idle: ResponseResource<Nothing>()
}
