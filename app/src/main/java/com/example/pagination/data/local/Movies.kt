package com.example.pagination.data.local

data class Movies(
    val page: Int,
    val results: List<MoviesItem>,
    val total_pages: Int,
    val total_results: Int
)
