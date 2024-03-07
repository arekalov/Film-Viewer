package com.pro.film_viewer.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pro.data.findfilmbyid.FilmRemoteDataSource
import com.pro.data.findfilmbyid.FilmRepository
import com.pro.data.models.Film
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailFilmViewModel : ViewModel() {
    val filmRepository = FilmRepository(FilmRemoteDataSource())
    val detailFilmLiveData = MutableLiveData<Film>()

    fun getDetailFilm(id: String) {
        viewModelScope.launch {
            val answer = filmRepository.getFilm(id)
            if (answer != null) {
                detailFilmLiveData.value = answer!!
            }
            else {
                Log.e("error", "getDetailFilm error")
            }
        }
    }

    fun observeDetailFilmLiveData(): LiveData<Film> = detailFilmLiveData;
}