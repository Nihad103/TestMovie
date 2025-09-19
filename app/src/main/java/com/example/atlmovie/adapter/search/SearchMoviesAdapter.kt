package com.example.atlmovie.adapter.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.atlmovie.adapter.OnMovieClickListener
import com.example.atlmovie.databinding.ItemSearchBinding
import com.example.atlmovie.model.search.Result

class SearchMoviesAdapter(
    private val listener: OnMovieClickListener
) : RecyclerView.Adapter<SearchMoviesAdapter.SearchViewHolder>() {

    private val items = arrayListOf<Result>()

    inner class SearchViewHolder(private val binding: ItemSearchBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Result) {
            binding.film = item
            binding.executePendingBindings()
            binding.root.setOnClickListener { listener.onMovieClick(item.id ?: 0) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val binding = ItemSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    fun updateList(newList: List<Result>) {
        items.clear()
        items.addAll(newList)
        notifyDataSetChanged()
    }
}
