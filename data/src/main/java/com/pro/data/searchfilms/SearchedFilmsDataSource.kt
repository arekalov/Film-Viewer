package com.pro.data.searchfilms

import com.pro.data.api.KinopoiskApiInstance
import com.pro.data.models.Film
import com.pro.data.models.SearchedFilms
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchedFilmsDataSource {
    suspend fun searchFilm(name: String): SearchedFilms? {
        var searchedFilms: SearchedFilms? = null
        try {

            KinopoiskApiInstance.api.searchFilms(name).enqueue(object : Callback<SearchedFilms> {
                override fun onResponse(call: Call<SearchedFilms>, response: Response<SearchedFilms>) {
                    searchedFilms = response.body()
                }

                override fun onFailure(call: Call<SearchedFilms>, t: Throwable) {
                    searchedFilms = null
                }
            })
            return searchedFilms
        } catch (ex: Exception) {
            return null
        }
    }
}