package com.pro.film_viewer.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pro.data.repositories.FilmRepository
import com.pro.data.models.Film
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailFilmViewModel : ViewModel() {
    val filmRepository = FilmRepository()
    val detailFilmLiveData = MutableLiveData<Film>()

    fun getDetailFilm(id: String) {
        viewModelScope.launch {
            filmRepository.getFilm(id).enqueue(object : Callback<Film>{
                override fun onResponse(call: Call<Film>, response: Response<Film>) {
                    if (response.body() != null && response.isSuccessful) {
                        detailFilmLiveData.value = response.body()
                    }
                    else {
                        Log.e("error", "getDetailFilm: null response body", )
                    }
                }

                override fun onFailure(call: Call<Film>, t: Throwable) {
                    Log.e("error", "getDetailFilm: error", )
                }

            })
        }
    }

    fun observeDetailFilmLiveData(): LiveData<Film> = detailFilmLiveData;
}