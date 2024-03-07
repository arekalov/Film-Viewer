package com.pro.data.api

import com.pro.data.models.Film
import com.pro.data.models.SearchedFilms
import com.pro.data.models.TopFilms
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

internal interface KinopoiskApi {

    @Headers(
        "X-API-KEY: e30ffed0-76ab-4dd6-b41f-4c9da2b2735b",
        "Content-Type: application/json"
    )
    @GET("api/v2.2/films/{id}")
    fun getFilmById(
        @Path("id") id: String
    ): Call<Film>

    @Headers(
        "X-API-KEY: e30ffed0-76ab-4dd6-b41f-4c9da2b2735b",
        "Content-Type: application/json"
    )
    @GET("api/v2.2/films/collections")
    suspend fun getTopFilms(
    ): Response<TopFilms>

    @Headers(
        "X-API-KEY: e30ffed0-76ab-4dd6-b41f-4c9da2b2735b",
        "Content-Type: application/json"
    )
    @GET("api/v2.1/films/search-by-keyword?")
    fun searchFilms(@Query("keyword") keyword: String): Call<SearchedFilms>

}