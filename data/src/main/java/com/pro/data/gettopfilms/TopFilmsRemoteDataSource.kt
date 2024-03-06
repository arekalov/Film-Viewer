package com.pro.data.gettopfilms

import com.pro.data.api.KinopoiskApiInstance
import com.pro.data.models.Film
import com.pro.data.models.TopFilms
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TopFilmsRemoteDataSource {
    suspend fun getTopFilms(): TopFilms? {
        var topFilms: TopFilms? = null
        try {

            KinopoiskApiInstance.api.getTopFilms().enqueue(object : Callback<TopFilms> {
                override fun onResponse(call: Call<TopFilms>, response: Response<TopFilms>) {
                    topFilms = response.body()
                }

                override fun onFailure(call: Call<TopFilms>, t: Throwable) {
                    topFilms = null
                }
            })
            return topFilms
        } catch (ex: Exception) {
            return null
        }
    }
}