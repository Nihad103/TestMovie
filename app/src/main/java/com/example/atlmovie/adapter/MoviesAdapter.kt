package com.example.atlmovie.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.atlmovie.databinding.ItemSeeAllBinding
import com.example.atlmovie.model.home.MovaResult

class MoviesAdapter(
    private val listener: OnMovieClickListener
) : RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {

    private val items = arrayListOf<MovaResult>()

    inner class MovieViewHolder(private val itemSeeAllBinding: ItemSeeAllBinding) :
        RecyclerView.ViewHolder(itemSeeAllBinding.root) {
        fun bind(item: MovaResult) {
            itemSeeAllBinding.film = item
            itemSeeAllBinding.executePendingBindings()

            itemSeeAllBinding.root.setOnClickListener {
                listener.onMovieClick(item.id ?: 0)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemSeeAllBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    fun updateList(newList: List<MovaResult>) {
        items.clear()
        items.addAll(newList)
        notifyDataSetChanged()
    }
}

