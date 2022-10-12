package com.example.nytimesmostpopularapp.data.model

import com.example.nytimesmostpopularapp.data.network.api.ApiService
import com.example.nytimesmostpopularapp.data.network.responses.MostViewApiResponse
import retrofit2.Response

class RemoteData(private val service: ApiService) :
    DataContract.Remote {
    override suspend fun getMostPopular(): Response<MostViewApiResponse> {
        return service.getMostPopular()
    }

}