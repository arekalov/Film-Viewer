package com.pro.data.models

data class TopFilms(
    val items: List<FilmOfTop>,
    val total: Int,
    val totalPages: Int
)