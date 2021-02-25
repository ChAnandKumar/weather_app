package com.demo.bookmarklocation.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.bookmarklocation.model.LocationModel
import com.demo.bookmarklocation.model.WeatherResponse
import com.demo.bookmarklocation.repository.LocationRepository
import com.demo.bookmarklocation.repository.ResultWrapper
import com.demo.bookmarklocation.repository.WeatherRepository
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationListViewModel  @Inject constructor(private val locationRepository: LocationRepository) : ViewModel() {
    // TODO: Implement the ViewModel
    private val locationListLiveData = MutableLiveData<List<LocationModel>?>()

    private val showProgressLiveData = MutableLiveData<Boolean>()
    private val errorMessageLiveData = MutableLiveData<String>()

    fun getLocationList() {
        viewModelScope.launch(Dispatchers.IO) {

            showProgressLiveData.postValue(true)
            val locationResponse = locationRepository.getLocationList()
            if (locationResponse != null) {
                if (locationResponse.isEmpty()) {
                    errorMessageLiveData.postValue("")
                } else {
                    locationListLiveData.postValue(locationResponse)
                }
            }
            showProgressLiveData.postValue(false)
        }
    }


//    fun getWeatherWithLocationList(latLng : LatLng) {
//        viewModelScope.launch(Dispatchers.Default) {
//
//            showProgressLiveData.postValue(true)
//            val weatherResponse = weatherRepository.getWeather(latLng)
//            if (weatherResponse != null) {
//                if (weatherResponse.weather.isEmpty()) {
//                    errorMessageLiveData.postValue("")
//                } else {
//                    weatherResponseLiveData.postValue(weatherResponse)
//                }
//            }
//            showProgressLiveData.postValue(false)
//        }
//    }

//    fun getWeatherWithLocationWithCity(city : String) {
//
//        viewModelScope.launch {
//            val redditResponse = weatherRepository.getWeatherWithCity(city)
//            when (redditResponse) {
//                is ResultWrapper.NetworkError -> showNetworkError()
//                is ResultWrapper.GenericError -> showGenericError(redditResponse)
//                is ResultWrapper.Success -> showSuccess(redditResponse.value)
//            }
//        }

//        viewModelScope.launch(Dispatchers.Default) {
//
//            showProgressLiveData.postValue(true)
//            val weatherResponse = weatherRepository.getWeatherWithCity(city)
//            if (weatherResponse != null) {
////                if (weatherResponse.weather.isEmpty()) {
////                    errorMessageLiveData.postValue("")
////                } else {
//                    weatherResponseLiveData.postValue(weatherResponse)
//                //}
//            }
//            showProgressLiveData.postValue(false)
//        }
 //   }




    fun observeMovieList(): MutableLiveData<List<LocationModel>?> {
        return locationListLiveData
    }




    fun observeProgress(): MutableLiveData<Boolean> {
        return showProgressLiveData
    }

    fun observeErrorMessage(): MutableLiveData<String> {
        return errorMessageLiveData
    }
}