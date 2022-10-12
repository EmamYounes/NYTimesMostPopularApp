package com.example.swensonheweatherapp.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.nytimesmostpopularapp.data.model.MyRepository

class MyViewModelFactory(
    private val myRepository: MyRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MyViewModel(myRepository) as T
    }
}