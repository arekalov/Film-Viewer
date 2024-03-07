package com.pro.film_viewer.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pro.data.repositories.TopFilmsRepository
import com.pro.data.models.FilmOfTop
import com.pro.data.models.TopFilms
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFilmsViewModel : ViewModel() {
    var topFilmsLiveData = MutableLiveData<List<FilmOfTop>>()
    val topFilmsRepository = TopFilmsRepository()

    fun getTopFilms() {
        viewModelScope.launch {
            topFilmsRepository.getTopFilms().enqueue(object : Callback<TopFilms>{
                override fun onResponse(call: Call<TopFilms>, response: Response<TopFilms>) {
                    if (response.body() != null) {
                        topFilmsLiveData.value = response.body()!!.items
                    }
                    else {
                        Log.e("error", "getTopFilms error")
                    }
                }

                override fun onFailure(call: Call<TopFilms>, t: Throwable) {
                    Log.e("error", "getTopFilms null body")
                }
            })
        }
    }

fun observeTopFilmsLiveData(): LiveData<List<FilmOfTop>> = topFilmsLiveData
}