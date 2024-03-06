package com.pro.data.gettopfilms

import com.pro.data.models.TopFilms

class TopFilmsRepository(private val topFilmsRemoteDataSource: TopFilmsRemoteDataSource) {
    suspend fun getTopFilms(): TopFilms? {
        return topFilmsRemoteDataSource.getTopFilms()
    }
}