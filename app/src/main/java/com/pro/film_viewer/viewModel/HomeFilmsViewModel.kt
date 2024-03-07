package com.pro.film_viewer.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.pro.data.gettopfilms.TopFilmsRemoteDataSource
import com.pro.data.gettopfilms.TopFilmsRepository
import com.pro.data.models.FilmOfTop
import kotlinx.coroutines.launch

class HomeFilmsViewModel : ViewModel() {
    var topFilmsLiveData = MutableLiveData<List<FilmOfTop>>()
    val topFilmsRepository = TopFilmsRepository(TopFilmsRemoteDataSource())

    fun getTopFilms() {
        viewModelScope.launch {
            val answer = topFilmsRepository.getTopFilms()
            if (answer != null) {
                topFilmsLiveData.value = answer.items!!
            } else {
                Log.e("error", "getDetailFilm error")
            }
        }
    }

fun observeTopFilmsLiveData(): LiveData<List<FilmOfTop>> = topFilmsLiveData
}