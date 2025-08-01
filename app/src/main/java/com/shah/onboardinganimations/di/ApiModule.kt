package com.shah.onboardinganimations.di

import com.shah.onboardinganimations.data.api.OnboardingApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import javax.inject.Singleton

/**
 * Created by Monil on 01/08/25.
 */

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    @Provides
    @Singleton
    fun provideOnboardingApi(client: HttpClient): OnboardingApi = OnboardingApi(client)
}
