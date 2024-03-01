package com.example.data.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val apiKey: String): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val originalHttpUrl = originalRequest.url()

        val modifiedHttpUrl = originalHttpUrl.newBuilder()
            .addQueryParameter("apiKey", apiKey)
            .build()

        val modifiedRequest = originalRequest.newBuilder()
            .url(modifiedHttpUrl)
            .build()

        return chain.proceed(modifiedRequest)
    }
}