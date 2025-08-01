package com.shah.onboardinganimations.data.repository

import com.shah.onboardinganimations.data.api.OnboardingApi
import com.shah.onboardinganimations.data.model.OnboardingResponse
import com.shah.onboardinganimations.utils.ResponseResource
import javax.inject.Inject

/**
 * Created by Monil on 01/08/25.
 */

class OnboardingRepository @Inject constructor(private val api: OnboardingApi) : BaseRepository() {

    suspend fun getOnboardingData(): ResponseResource<OnboardingResponse> {
        return safeApiCall {
            api.getOnboardingDetails()
        }
    }
}
