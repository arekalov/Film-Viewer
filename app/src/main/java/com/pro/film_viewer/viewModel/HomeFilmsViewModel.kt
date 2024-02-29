package com.pro.film_viewer.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pro.film_viewer.pojo.Item
import com.pro.film_viewer.pojo.TopFilms
import com.pro.film_viewer.retrofit.KinopoiskApiInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFilmsViewModel : ViewModel() {
    val topFilmsLiveData = MutableLiveData<List<Item>>()

    fun getTopFilms(){
        KinopoiskApiInstance.api.getTopFilms().enqueue(object : Callback<TopFilms>{
            override fun onResponse(call: Call<TopFilms>, response: Response<TopFilms>) {
                if (response.body() != null) {
                    topFilmsLiveData.value = response.body()!!.items
                }
                else {
                    Log.e("Error", "Empty body.string")
                }
            }

            override fun onFailure(call: Call<TopFilms>, t: Throwable) {
                Log.e("getTopFilms error", t.message.toString())
            }

        })
    }

    fun observeTopFilmsLiveData(): LiveData<List<Item>> = topFilmsLiveData
}