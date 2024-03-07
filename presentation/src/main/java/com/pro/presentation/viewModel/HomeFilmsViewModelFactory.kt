package com.pro.film_viewer.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class HomeFilmsViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeFilmsViewModel() as T
    }
}