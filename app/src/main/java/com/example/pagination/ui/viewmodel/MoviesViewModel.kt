package com.example.pagination.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.pagination.data.remote.MoviesDataSource
import com.example.pagination.network.MyApi

class MoviesViewModel(
    private val api: MyApi
) : ViewModel() {
    val movies =
        Pager(config = PagingConfig(pageSize = 10), pagingSourceFactory = {
            MoviesDataSource(api)
        }).flow.cachedIn(viewModelScope)
}
