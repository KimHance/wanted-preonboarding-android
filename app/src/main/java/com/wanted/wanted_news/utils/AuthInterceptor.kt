package com.wanted.wanted_news.utils

import okhttp3.Interceptor

class AuthInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain) =
        chain.proceed(
            chain.request().newBuilder().apply {
                header("X-Api-Key", API_KEY)
            }.build()
        )

    companion object {
        private const val API_KEY = "0ca69815d0fc406c8640298e2380abfe"
    }
}