package com.pro.film_viewer.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pro.data.gettopfilms.TopFilmsRemoteDataSource
import com.pro.data.gettopfilms.TopFilmsRepository
import com.pro.data.models.FilmOfTop
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFilmsViewModel : ViewModel() {
    var topFilmsLiveData = MutableLiveData<List<FilmOfTop>>()
    val topFilmsRepository = TopFilmsRepository(TopFilmsRemoteDataSource())

    fun getTopFilms() {
//        val job = viewModelScope.launch(Dispatchers.IO) {
//            val answer = topFilmsRepository.getTopFilms()
//            withContext(Dispatchers.Main) {
//                if (answer != null) {
//                    topFilmsLiveData.postValue(answer.items)
//                }
//            }
//        }
        topFilmsLiveData.value = topFilmsRepository.getTopFilms()!!.items
    }

    fun observeTopFilmsLiveData(): LiveData<List<FilmOfTop>> = topFilmsLiveData
}