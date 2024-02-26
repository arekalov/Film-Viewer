package com.pro.film_viewer.pojo

data class TopFilms(
    val items: List<Item>,
    val total: Int,
    val totalPages: Int
)