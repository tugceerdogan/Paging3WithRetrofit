package com.example.pagination.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pagination.data.local.MoviesItem
import com.example.pagination.databinding.ItemMovieBinding

class MoviesAdapter :
    PagingDataAdapter<MoviesItem, MoviesAdapter.MoviesViewHolder>(MoviesComparator) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MoviesViewHolder {
        return MoviesViewHolder(
            ItemMovieBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val item = getItem(position)
        item?.let { holder.bindMovie(it) }
    }

    inner class MoviesViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindMovie(item: MoviesItem) = with(binding) {
            Glide.with(listImageView.context)
                .load("https://image.tmdb.org/t/p/w500" + item.poster_path)
                .into(listImageView)
            listTitleText.text = item.title
            listOverviewText.text = item.overview
        }
    }

    object MoviesComparator : DiffUtil.ItemCallback<MoviesItem>() {
        override fun areItemsTheSame(oldItem: MoviesItem, newItem: MoviesItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MoviesItem, newItem: MoviesItem): Boolean {
            return oldItem == newItem
        }
    }
}
