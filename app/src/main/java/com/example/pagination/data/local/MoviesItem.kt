package com.example.pagination.data.local

data class MoviesItem(
    val id: Int,
    val overview: String,
    val poster_path: String,
    val title: String,
    val vote_average: Double,
    val release_date: String,
)
