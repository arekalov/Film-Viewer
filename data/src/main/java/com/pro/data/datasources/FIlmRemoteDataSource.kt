package com.pro.data.datasources

import com.pro.data.retrofit.KinopoiskApiInstance
import com.pro.film_viewer.pojo.Film
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FIlmRemoteDataSource {
    fun getDetailFilm(id : String) {
        KinopoiskApiInstance.api.getFilmById(id).enqueue(object : Callback<Film> {
            override fun onResponse(call: Call<Film>, response: Response<Film>) {
                if (response.body() != null) {
                    detailFilmLiveData.value = response.body()
                }
                else {
                    Log.e("error", "null body during getDetailFilm")
                }
            }

            override fun onFailure(call: Call<Film>, t: Throwable) {

            }

        })
    }
}