package com.pro.data.repositories

import com.pro.data.api.KinopoiskApiInstance
import com.pro.data.models.SearchedFilms
import retrofit2.Call

class SearchedFilmsRepository() {
    fun getSearchedFilms(name: String): Call<SearchedFilms> {
        return KinopoiskApiInstance.api.searchFilms(name)
    }
}