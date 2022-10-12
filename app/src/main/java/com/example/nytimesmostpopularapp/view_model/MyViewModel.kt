package com.example.swensonheweatherapp.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nytimesmostpopularapp.data.model.MyRepository
import com.example.nytimesmostpopularapp.data.network.responses.MostViewApiResponse
import com.example.nytimesmostpopularapp.data.network.responses.ResultsItem
import com.example.nytimesmostpopularapp.utils.Coroutines

class MyViewModel(
    private val myRepository: MyRepository
) : ViewModel() {

    var mostPopularApiResponse = MutableLiveData<MostViewApiResponse>()
    var errorException = MutableLiveData<String>()

    val itemSelected = MutableLiveData<ResultsItem>()

    suspend fun callMostPopularApi() {
        Coroutines.io {
            try {
                val response = myRepository.getMostPopular()
                mostPopularApiResponse.postValue(response)
            } catch (e: Exception) {
                errorException.postValue(e.toString())
                e.printStackTrace()
            }
        }
    }
}