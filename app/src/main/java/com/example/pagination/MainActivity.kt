package com.example.pagination

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pagination.databinding.ActivityMainBinding
import com.example.pagination.network.MyApi
import com.example.pagination.ui.MoviesAdapter
import com.example.pagination.ui.viewmodel.MoviesViewModel
import com.example.pagination.ui.viewmodel.MoviesViewModelFactory
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    lateinit var moviesViewModel: MoviesViewModel
    lateinit var moviesAdapter: MoviesAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        setupViewModel()
        setupView()
        setupList()
    }

    private fun setupViewModel() {
        val factory = MoviesViewModelFactory(MyApi())
        moviesViewModel = ViewModelProvider(this, factory).get(MoviesViewModel::class.java)
    }

    private fun setupView() {
        moviesAdapter = MoviesAdapter()
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = moviesAdapter
            setHasFixedSize(true)
        }
    }

    private fun setupList() {
        lifecycleScope.launch {
            moviesViewModel.movies.collectLatest { pagedData ->
                moviesAdapter.submitData(pagedData)
            }
        }
    }
}
