package com.example.nytimesmostpopularapp.data.network.interceptor

import com.example.nytimesmostpopularapp.utils.ApiConstant
import okhttp3.Interceptor
import okhttp3.Response

class AuthTokenInterceptor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val originalRequest = chain.request()
        val requestBuilder = originalRequest.newBuilder()
            .header("api-key", ApiConstant.APP_ID)
        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}