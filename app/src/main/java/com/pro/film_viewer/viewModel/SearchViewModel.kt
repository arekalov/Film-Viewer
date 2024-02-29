package com.pro.film_viewer.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pro.film_viewer.pojo.SearchedFilms
import com.pro.film_viewer.retrofit.KinopoiskApiInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchViewModel : ViewModel() {
    val searchedFilmsLiveData = MutableLiveData<SearchedFilms>()
    fun searchFilms(keyword: String) {
        KinopoiskApiInstance.api.searchFilms(keyword).enqueue(object : Callback<SearchedFilms>{
            override fun onResponse(call: Call<SearchedFilms>, response: Response<SearchedFilms>) {
                if (response.body() != null){
                    searchedFilmsLiveData.value = response.body()
                }
                else{
                    Log.e("error", "response.body = null during searchFilms")
                }
            }

            override fun onFailure(call: Call<SearchedFilms>, t: Throwable) {
                Log.e("error", "error during searchFilms")
            }

        })
    }

    fun observeSearchedFilmsLiveData(): LiveData<SearchedFilms> = searchedFilmsLiveData
}