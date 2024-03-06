package com.pro.data.searchfilms

import com.pro.data.models.SearchedFilms

class SearchedFilmsRepository(private val searchedFilmsDataSource: SearchedFilmsDataSource) {
    suspend fun getSearchedFilms(name: String): SearchedFilms? {
        return searchedFilmsDataSource.searchFilm(name)
    }
}