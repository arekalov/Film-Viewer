package com.pro.film_viewer.pojo

data class SearchedFilms(
    val films: List<FilmSearched>,
    val keyword: String,
    val pagesCount: Int,
    val searchFilmsCountResult: Int
)