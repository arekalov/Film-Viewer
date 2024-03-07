package com.pro.film_viewer.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.pro.data.gettopfilms.TopFilmsRepository
import com.pro.data.models.FilmOfTop
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeFilmsViewModel : ViewModel() {
    var topFilmsLiveData = MutableLiveData<List<FilmOfTop>>()
    val topFilmsRepository = TopFilmsRepository()

    fun getTopFilms() {
        viewModelScope.launch {
            val answer = topFilmsRepository.getTopFilms()
            if (true) {
                topFilmsLiveData.postValue(answer!!.items)
            }
        }
    }

fun observeTopFilmsLiveData(): LiveData<List<FilmOfTop>> = topFilmsLiveData
}