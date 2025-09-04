package com.example.atlmovie.adapter.pager

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.atlmovie.adapter.OnMovieClickListener
import com.example.atlmovie.databinding.ItemTrailersBinding
import com.example.atlmovie.model.detail.MovaDetail
import com.example.atlmovie.model.home.MovaResult

class TrailersAdapter(
    private val listener: OnMovieClickListener
) : RecyclerView.Adapter<TrailersAdapter.TrailersViewHolder>() {

    private val itemsPopular = arrayListOf<MovaResult>()

    inner class TrailersViewHolder(private val itemTrailersBinding: ItemTrailersBinding) : RecyclerView.ViewHolder(itemTrailersBinding.root) {
        fun bind(item: MovaResult) {
            itemTrailersBinding.mova = item
            itemTrailersBinding.root.setOnClickListener {
                listener.onMovieClick(item.id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrailersViewHolder {
        val view = ItemTrailersBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TrailersViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itemsPopular.size
    }

    override fun onBindViewHolder(holder: TrailersViewHolder, position: Int) {
        val itemPopular = itemsPopular[position]

        holder.bind(itemPopular)
    }

    fun updateList(newList: ArrayList<MovaResult>) {
        itemsPopular.clear()
        itemsPopular.addAll(newList)
        notifyDataSetChanged()
    }
}