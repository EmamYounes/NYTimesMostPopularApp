package com.example.nytimesmostpopularapp.data.network.api

import com.example.nytimesmostpopularapp.data.network.responses.MostViewApiResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("svc/mostpopular/v2/viewed/7.json?api-key=s50a4A70o2RmPHGWDY9ORmtv6VNoUfCi")
    suspend fun getMostPopular(): Response<MostViewApiResponse>
}