package com.example.pagination.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pagination.network.MyApi

class MoviesViewModelFactory(
    private val api: MyApi
) : ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MoviesViewModel(api) as T
    }
}
