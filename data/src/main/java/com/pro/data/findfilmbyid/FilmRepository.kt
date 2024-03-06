package com.pro.data.findfilmbyid

import com.pro.data.models.Film

class FilmRepository(private val filmRemoteDataSource: FilmRemoteDataSource) {
    suspend fun getFilm(id: String): Film? {
        return filmRemoteDataSource.getFilm(id)
    }
}