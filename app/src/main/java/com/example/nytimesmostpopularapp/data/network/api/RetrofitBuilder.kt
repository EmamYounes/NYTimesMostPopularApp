package com.example.nytimesmostpopularapp.data.network.api

import com.example.nytimesmostpopularapp.data.network.interceptor.AuthTokenInterceptor
import com.example.nytimesmostpopularapp.data.network.interceptor.NetworkConnectionInterceptor
import com.example.nytimesmostpopularapp.data.network.interceptor.ResponseInterceptor
import com.example.nytimesmostpopularapp.utils.ApiConstant
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitBuilder {


    companion object {

        private fun getRetrofit(okkHttpclient: OkHttpClient): Retrofit {
            return Retrofit.Builder()
                .client(okkHttpclient)
                .baseUrl(ApiConstant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }


        operator fun invoke(networkConnectionInterceptor: NetworkConnectionInterceptor): ApiService {

            val okkHttpclient: OkHttpClient = OkHttpClient.Builder()
                .addInterceptor(networkConnectionInterceptor)
                .addInterceptor(ResponseInterceptor())
                .addInterceptor(AuthTokenInterceptor())
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
                .build()
            return getRetrofit(okkHttpclient).create(ApiService::class.java)
        }
    }
}