package com.pro.data.findfilmbyid

import com.pro.data.api.KinopoiskApi
import com.pro.data.api.KinopoiskApiInstance
import com.pro.data.models.Film
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FilmRemoteDataSource {
    suspend fun getFilm(id: String): Film? {
        var film: Film? = null
        try {

            KinopoiskApiInstance.api.getFilmById(id).enqueue(object : Callback<Film> {
                override fun onResponse(call: Call<Film>, response: Response<Film>) {
                    film = response.body()
                }

                override fun onFailure(call: Call<Film>, t: Throwable) {
                    film = null
                }
            })
            return film
        } catch (ex: Exception) {
            return null
        }
    }
}