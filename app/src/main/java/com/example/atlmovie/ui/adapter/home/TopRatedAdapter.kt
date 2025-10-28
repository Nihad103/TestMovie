package com.example.atlmovie.ui.adapter.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.atlmovie.databinding.ItemTopRatedBinding
import com.example.atlmovie.model.home.MovaResult
import com.example.atlmovie.ui.adapter.OnMovieClickListener

class TopRatedAdapter(
    private val listener: OnMovieClickListener
) : Adapter<TopRatedAdapter.TopRatedViewHolder>(){

    private val itemsTopRated = arrayListOf<MovaResult>()

    inner class TopRatedViewHolder(private val itemTopRatedBinding: ItemTopRatedBinding) : RecyclerView.ViewHolder(itemTopRatedBinding.root) {
        fun bind(item: MovaResult) {
            itemTopRatedBinding.movaTopRated = item
            itemTopRatedBinding.root.setOnClickListener {
                listener.onMovieClick(item.id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopRatedViewHolder {
        val view = ItemTopRatedBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TopRatedViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itemsTopRated.take(10).size
    }

    override fun onBindViewHolder(holder: TopRatedViewHolder, position: Int) {
        val itemTopRated = itemsTopRated[position]

        holder.bind(itemTopRated)
    }


    fun updateList(newList: ArrayList<MovaResult>) {
        itemsTopRated.clear()
        itemsTopRated.addAll(newList)
        notifyDataSetChanged()
    }
}