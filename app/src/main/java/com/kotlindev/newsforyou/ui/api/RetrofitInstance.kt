package com.kotlindev.newsforyou.ui.api

import com.kotlindev.newsforyou.ui.util.Constants.Companion.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {
    companion object {
        //lazy means we initialize it here ONLY ONCE
        private val retrofit by lazy {
            val logging = HttpLoggingInterceptor() //attach this to retrofit obj to see what request we are actually making and its contents
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder() //use that interceptor to create client
                .addInterceptor(logging)
                .build()
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) // we need to add converter factory to convert JSON object to Java object
                .client(client)
                .build()
        }
        //this api we will be able to use to make network requests
        val api by lazy {
            retrofit.create(NewsAPI::class.java)
        }
    }
}