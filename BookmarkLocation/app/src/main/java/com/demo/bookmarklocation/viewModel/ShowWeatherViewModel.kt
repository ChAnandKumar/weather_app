package com.demo.bookmarklocation.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.bookmarklocation.model.WeatherResponse
import com.demo.bookmarklocation.repository.ResultWrapper
import com.demo.bookmarklocation.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShowWeatherViewModel @Inject constructor(private val weatherRepository: WeatherRepository): ViewModel() {
    private val weatherResponseLiveData = MutableLiveData<WeatherResponse>()
    fun getWeatherWithLocationWithCity(city : String) {

        viewModelScope.launch {
            var redditResponse = weatherRepository.getWeatherWithCity(city)
            when (redditResponse) {
                is ResultWrapper.NetworkError -> showNetworkError()
                is ResultWrapper.GenericError -> showGenericError(redditResponse)
                is ResultWrapper.Success -> showSuccess(redditResponse.value)
            }
        }
    }
    private fun showSuccess(weatherResponse: WeatherResponse) {
        Log.e("anand","showSuccess is :$weatherResponse")
        weatherResponseLiveData.postValue(weatherResponse)
    }

    private fun showGenericError(weatherResponseError: ResultWrapper.GenericError) {
        Log.e("anand","Error is :$weatherResponseError")
    }

    private fun showNetworkError() {
        Log.e("anand","Error is : Network error")
    }

    fun observeWeatherData(): MutableLiveData<WeatherResponse> {
        return weatherResponseLiveData
    }

}