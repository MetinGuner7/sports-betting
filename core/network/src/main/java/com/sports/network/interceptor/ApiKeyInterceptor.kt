package com.sports.network.interceptor


import com.sports.network.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiKeyInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val originalHttpUrl = originalRequest.url

        if (originalHttpUrl.queryParameter("apiKey") != null) {
            return chain.proceed(originalRequest)
        }
        val newUrl = originalHttpUrl.newBuilder()
            .addQueryParameter("apiKey", BuildConfig.ODDS_API_KEY)
            .build()

        val newRequest = originalRequest.newBuilder()
            .url(newUrl)
            .build()

        return chain.proceed(newRequest)
    }
}