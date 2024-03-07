package com.pro.data.gettopfilms

import com.pro.data.api.KinopoiskApiInstance
import com.pro.data.models.TopFilms

class TopFilmsRepository() {
    suspend fun getTopFilms(): TopFilms? {
        return KinopoiskApiInstance.api.getTopFilms().body()
    }
}