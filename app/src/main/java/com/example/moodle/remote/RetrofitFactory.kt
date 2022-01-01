package com.example.moodle.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


internal lateinit var courseApi : CourseEndPoints
object RetrofitFactory {
    private const val baseUrl = "https://"
    private var retrofit: Retrofit? = null

    fun configure() {
        courseApi = initRetrofit(CourseEndPoints::class.java)
    }

    private fun <T> initRetrofit(service: Class<T>): T = retrofit?.create(service) ?: run {
        retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit!!.create(service)
    }
}