package com.shah.onboardinganimations.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shah.onboardinganimations.data.model.OnboardingResponse
import com.shah.onboardinganimations.data.repository.OnboardingRepository
import com.shah.onboardinganimations.utils.ResponseResource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Monil on 01/08/25.
 */

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val repository: OnboardingRepository
) : ViewModel() {

    private val _onboardingResult = MutableStateFlow<ResponseResource<OnboardingResponse>?>(null)
    val onboardingResult: StateFlow<ResponseResource<OnboardingResponse>?> =
        _onboardingResult.asStateFlow()


    fun fetchOnboarding() = viewModelScope.launch {
        _onboardingResult.value = repository.getOnboardingData()
    }
}
