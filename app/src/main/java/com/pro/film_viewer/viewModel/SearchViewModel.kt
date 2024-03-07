package com.pro.film_viewer.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pro.data.models.SearchedFilms
import com.pro.data.repositories.SearchedFilmsRepository
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchViewModel : ViewModel() {
    val searchedFilmsLiveData = MutableLiveData<SearchedFilms>()
    val searchedFilmsRepository = SearchedFilmsRepository()
    fun searchFilms(keyword: String) {
        viewModelScope.launch {
            searchedFilmsRepository.getSearchedFilms(keyword)
                .enqueue(object : Callback<SearchedFilms> {
                    override fun onResponse(
                        call: Call<SearchedFilms>,
                        response: Response<SearchedFilms>
                    ) {
                        if (response.body() != null && response.isSuccessful) {
                            searchedFilmsLiveData.value = response.body()
                        } else {
                            Log.e("error", "getSearchedFilms: null body")
                        }
                    }

                    override fun onFailure(call: Call<SearchedFilms>, t: Throwable) {
                        Log.e("error", "getSearchedFilms: error")
                    }
                })
        }
    }

    fun observeSearchedFilmsLiveData(): LiveData<SearchedFilms> = searchedFilmsLiveData
}