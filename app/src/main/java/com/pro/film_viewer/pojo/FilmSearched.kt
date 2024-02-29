package com.pro.film_viewer.pojo

data class FilmSearched(
    val countries: List<Country>,
    val description: String,
    val filmId: Int,
    val filmLength: String,
    val genres: List<Genre>,
    val nameEn: String,
    val nameRu: String,
    val posterUrl: String,
    val posterUrlPreview: String,
    val rating: String,
    val ratingVoteCount: Int,
    val type: String,
    val year: String
){
    public fun getStringGenres(): String {
        return genres.joinToString(separator = ", ")
    }
    public fun getStringCountries(): String {
        return countries.joinToString(separator = ", ")
    }
}