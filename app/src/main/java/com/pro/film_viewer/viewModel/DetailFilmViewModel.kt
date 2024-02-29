package com.pro.film_viewer.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pro.film_viewer.pojo.Film
import com.pro.film_viewer.retrofit.KinopoiskApiInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailFilmViewModel : ViewModel() {
    val detailFilmLiveData = MutableLiveData<Film>()

    fun getDetailFilm(id : String) {
        KinopoiskApiInstance.api.getFilmById(id).enqueue(object : Callback<Film>{
            override fun onResponse(call: Call<Film>, response: Response<Film>) {
                if (response.body() != null) {
                    detailFilmLiveData.value = response.body()
                }
                else {
                    Log.e("error", "null body during getDetailFilm")
                }
            }

            override fun onFailure(call: Call<Film>, t: Throwable) {
                Log.e("error", "error during getDetailFilm")
            }

        })
    }

    fun observeDetailFilmLiveData(): LiveData<Film> = detailFilmLiveData;
}