package com.pro.data.repositories

import com.pro.data.api.KinopoiskApiInstance
import com.pro.data.models.TopFilms
import retrofit2.Call
import retrofit2.Response

class TopFilmsRepository() {
    fun getTopFilms(): Call<TopFilms> {
        return KinopoiskApiInstance.api.getTopFilms()
    }
}