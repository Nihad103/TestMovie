package com.example.atlmovie.ui.adapter.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.atlmovie.databinding.ItemPopularBinding
import com.example.atlmovie.model.home.MovaResult
import com.example.atlmovie.ui.adapter.OnMovieClickListener

class PopularAdapter(
    private val listener: OnMovieClickListener
) : RecyclerView.Adapter<PopularAdapter.PopularViewHolder>() {

    private val itemsPopular = arrayListOf<MovaResult>()

    inner class PopularViewHolder(private val itemPopularBinding: ItemPopularBinding) : RecyclerView.ViewHolder(itemPopularBinding.root) {
        fun bind(item: MovaResult) {
            itemPopularBinding.movaPopular = item
            itemPopularBinding.root.setOnClickListener {
                listener.onMovieClick(item.id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularViewHolder {
        val view = ItemPopularBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PopularViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itemsPopular.take(10).size
    }

    override fun onBindViewHolder(holder: PopularViewHolder, position: Int) {
        val itemPopular = itemsPopular[position]

        holder.bind(itemPopular)
    }

    fun updateList(newList: ArrayList<MovaResult>) {
        itemsPopular.clear()
        itemsPopular.addAll(newList)
        notifyDataSetChanged()
    }

}