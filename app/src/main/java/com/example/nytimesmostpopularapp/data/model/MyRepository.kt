package com.example.nytimesmostpopularapp.data.model

import com.example.nytimesmostpopularapp.data.network.responses.MostViewApiResponse
import com.example.sampleapp.data.network.request.SafeApiRequest

class MyRepository(
    private val remote: DataContract.Remote
) : DataContract.Repository, SafeApiRequest() {
    override suspend fun getMostPopular(): MostViewApiResponse {
        return apiRequest { remote.getMostPopular() }
    }


}