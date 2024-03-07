package com.pro.film_viewer.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pro.data.models.SearchedFilms
import com.pro.data.searchfilms.SearchedFilmsDataSource
import com.pro.data.searchfilms.SearchedFilmsRepository
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchViewModel : ViewModel() {
    val searchedFilmsLiveData = MutableLiveData<SearchedFilms>()
    val searchedFilmsRepository = SearchedFilmsRepository(SearchedFilmsDataSource())
    fun searchFilms(keyword: String) {
        viewModelScope.launch {
            val answer = searchedFilmsRepository.getSearchedFilms(keyword)
            if (answer != null) {
                searchedFilmsLiveData.value = answer!!
            }
        }
    }

    fun observeSearchedFilmsLiveData(): LiveData<SearchedFilms> = searchedFilmsLiveData
}