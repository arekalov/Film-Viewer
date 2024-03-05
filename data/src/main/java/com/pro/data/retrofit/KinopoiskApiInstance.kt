package com.pro.data.retrofit
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
object KinopoiskApiInstance {
    val api: KinopoiskApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://kinopoiskapiunofficial.tech/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(KinopoiskApi::class.java)
    }
}