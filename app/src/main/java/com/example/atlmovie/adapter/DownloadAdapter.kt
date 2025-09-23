package com.example.atlmovie.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.atlmovie.databinding.ItemDownloadBinding
import com.example.atlmovie.databinding.ItemMyListBinding
import com.example.atlmovie.model.download.DownloadEntity
import com.example.atlmovie.utils.loadImageUrl

class DownloadAdapter(
    private var movies: List<DownloadEntity> = emptyList(),
    private val listener: OnMovieClickListener,
    private val onDeleteClick: (DownloadEntity) -> Unit
) : RecyclerView.Adapter<DownloadAdapter.DownloadViewHolder>() {

    private var filteredList: List<DownloadEntity> = movies

    inner class DownloadViewHolder(private val binding: ItemDownloadBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: DownloadEntity) {
            binding.imgDownload.loadImageUrl(movie.posterPath)
            binding.tvFilmName.text = movie.title
            binding.tvSize.text = movie.voteCount.toString()
            binding.tvFilmDate.text = movie.releaseDate ?: "Unknown"

            binding.root.setOnClickListener {
                listener.onMovieClick(movie.id)
            }

            binding.imgDelete.setOnClickListener {
                onDeleteClick(movie)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DownloadViewHolder {
        val binding = ItemDownloadBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return DownloadViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DownloadViewHolder, position: Int) {
        holder.bind(filteredList[position])
    }

    override fun getItemCount(): Int = filteredList.size

    fun updateList(newList: List<DownloadEntity>) {
        movies = newList
        filteredList = newList
        notifyDataSetChanged()
    }

    fun filter(query: String) {
        filteredList = if (query.isEmpty()) {
            movies
        } else {
            movies.filter { it.title.contains(query, ignoreCase = true) }
        }
        notifyDataSetChanged()
    }
}