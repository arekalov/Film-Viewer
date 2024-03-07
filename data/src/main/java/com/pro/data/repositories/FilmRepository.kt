package com.pro.data.repositories

import com.pro.data.api.KinopoiskApiInstance
import com.pro.data.models.Film
import retrofit2.Call

class FilmRepository() {
    suspend fun getFilm(id: String): Call<Film> {
        return  KinopoiskApiInstance.api.getFilmById(id)
    }
}