package com.demo.bookmarklocation.viewModel

import android.content.Context
import android.location.Geocoder
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.demo.bookmarklocation.model.LocationModel
import com.demo.bookmarklocation.repository.LocationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class MapsViewModel @Inject constructor(private val locationRepository: LocationRepository) : ViewModel() {
    private val locationListLiveData = MutableLiveData<List<LocationModel>?>()
    private val showProgressLiveData = MutableLiveData<Boolean>()
    private val errorMessageLiveData = MutableLiveData<String>()
    private val insertedMessageLiveData = MutableLiveData<String>()

    fun getLocationList() {
        viewModelScope.launch(IO) {

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

    fun observeMovieList(): MutableLiveData<List<LocationModel>?> {
        return locationListLiveData
    }

    fun observeProgress(): MutableLiveData<Boolean> {
        return showProgressLiveData
    }

    fun observeErrorMessage(): MutableLiveData<String> {
        return errorMessageLiveData
    }


    fun observeInsertMessage(): MutableLiveData<String> {
        return insertedMessageLiveData
    }

    fun addLocation(latLug: LocationModel){
        viewModelScope.launch(IO){
            locationRepository.addLocation(latLug)
            insertedMessageLiveData.postValue("Inserted")
        }
    }

}