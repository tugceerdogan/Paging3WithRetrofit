package com.example.pagination.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.pagination.data.local.MoviesItem
import com.example.pagination.network.MyApi

class MoviesDataSource(private val api: MyApi) : PagingSource<Int, MoviesItem>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MoviesItem> {
        return try {
            val nextPageNumber = params.key ?: 1
            val response = api.getMovies(nextPageNumber)
            println(response)

            LoadResult.Page(
                data = response.results,
                prevKey = if (nextPageNumber > 0) nextPageNumber - 1 else null,
                nextKey = if (nextPageNumber < response.total_pages) nextPageNumber + 1 else null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, MoviesItem>): Int? {
        TODO("Not yet implemented")
    }
}
