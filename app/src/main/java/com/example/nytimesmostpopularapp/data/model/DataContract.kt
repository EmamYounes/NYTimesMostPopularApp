package com.example.nytimesmostpopularapp.data.model

import com.example.nytimesmostpopularapp.data.network.responses.MostViewApiResponse
import retrofit2.Response

interface DataContract {
    interface Repository {
        suspend fun getMostPopular(): MostViewApiResponse
    }

    interface Remote {
        suspend fun getMostPopular(): Response<MostViewApiResponse>
    }
}