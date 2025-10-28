package com.example.atlmovie.ui.adapter.mylist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.atlmovie.databinding.ItemMyListBinding
import com.example.atlmovie.model.mylist.MyListEntity
import com.example.atlmovie.ui.adapter.OnMovieClickListener
import com.example.atlmovie.utils.loadImageUrl

class MyListAdapter(
    private val listener: OnMovieClickListener,
    private val onDeleteClick: (MyListEntity) -> Unit,
) : RecyclerView.Adapter<MyListAdapter.MyListViewHolder>() {

    private val originalList = arrayListOf<MyListEntity>()
    private val filteredList = arrayListOf<MyListEntity>()

    fun updateList(newList: List<MyListEntity>) {
        originalList.clear()
        originalList.addAll(newList)
        filteredList.clear()
        filteredList.addAll(newList)
        notifyDataSetChanged()
    }

    fun filter(query: String) {
        filteredList.clear()
        if (query.isEmpty()) {
            filteredList.addAll(originalList)
        } else {
            filteredList.addAll(
                originalList.filter { it.title.contains(query, ignoreCase = true) }
            )
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyListViewHolder {
        val binding = ItemMyListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyListViewHolder(binding)
    }

    override fun getItemCount(): Int = filteredList.size

    override fun onBindViewHolder(holder: MyListViewHolder, position: Int) {
        holder.bind(filteredList[position])
    }

    inner class MyListViewHolder(private val binding: ItemMyListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: MyListEntity) {
            binding.imgMyList.loadImageUrl(movie.posterPath)
            binding.tvFilmName.text = movie.title
            binding.tvFilmDate.text = movie.releaseDate ?: "Unknown"
            binding.tvSize.text = movie.voteCount.toString()

            binding.imgDelete.setOnClickListener { onDeleteClick(movie) }
            binding.root.setOnClickListener { listener.onMovieClick(movie.id) }

        }
    }
}
