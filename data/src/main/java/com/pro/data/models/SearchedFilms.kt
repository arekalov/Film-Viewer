package com.pro.data.models

data class SearchedFilms(
    val films: List<FilmSearched>,
    val keyword: String,
    val pagesCount: Int,
    val searchFilmsCountResult: Int
)