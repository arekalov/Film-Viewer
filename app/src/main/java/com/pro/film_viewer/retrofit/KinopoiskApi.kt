package com.pro.film_viewer.retrofit

import com.pro.film_viewer.pojo.Film
import com.pro.film_viewer.pojo.TopFilms
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface KinopoiskApi {


    @GET("api/v2.2/films")
    fun getFilmById(@Query("id") id: String): Call<Film>

    @GET("api/v2.2/films/collections")
    fun getTopFilms(@Header("X-API-KEY") key: String, @Header("Content-Type") type: String): Call<TopFilms>

}