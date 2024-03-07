//package com.pro.data.gettopfilms
//
//import com.pro.data.api.KinopoiskApiInstance
//import com.pro.data.api.KinopoiskApiInstance.api
//import com.pro.data.models.Film
//import com.pro.data.models.TopFilms
//
//class TopFilmsRemoteDataSource {
//    suspend fun getTopFilms(): TopFilms? {
//        val response = api.getTopFilms()
//        return if (response.isSuccessful) {
//            response.body()
//        } else {
//            null
//        }
//    }
//}