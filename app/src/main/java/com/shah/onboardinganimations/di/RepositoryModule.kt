package com.shah.onboardinganimations.di

import com.shah.onboardinganimations.data.api.OnboardingApi
import com.shah.onboardinganimations.data.repository.OnboardingRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Monil on 01/08/25.
 */

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideOnboardingRepository(api: OnboardingApi): OnboardingRepository = OnboardingRepository(api)
}
