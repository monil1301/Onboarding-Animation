package com.shah.onboardinganimations.data.api

import com.shah.onboardinganimations.data.model.OnboardingResponse
import com.shah.onboardinganimations.utils.Constants
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.http.contentType
import javax.inject.Inject

/**
 * Created by Monil on 01/08/25.
 */

class OnboardingApi @Inject constructor(
    private val client: HttpClient
) {
    suspend fun getOnboardingDetails(): OnboardingResponse =
        client.get(Constants.API.PATH.ONBOARDING) {
            contentType(ContentType.Application.Json)
        }.body()
}
