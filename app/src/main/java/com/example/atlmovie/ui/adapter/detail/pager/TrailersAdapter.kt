package com.example.atlmovie.ui.adapter.detail.pager

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.atlmovie.databinding.ItemTrailersBinding
import com.example.atlmovie.model.openyoutube.Result
import com.example.atlmovie.ui.adapter.detail.trailerclickinterface.OnTrailerClickListener

class TrailersAdapter(
    private val listener: OnTrailerClickListener,
    private var trailers: List<Result> = emptyList()
) : RecyclerView.Adapter<TrailersAdapter.TrailerViewHolder>() {

    fun updateList(newList: List<Result>) {
        trailers = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrailerViewHolder {
        val binding = ItemTrailersBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TrailerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TrailerViewHolder, position: Int) {
        val trailer = trailers[position]
        holder.bind(trailer)

        holder.binding.root.setOnClickListener {
            listener.onTrailerClick(trailer.key)
        }
    }

    override fun getItemCount(): Int = trailers.size

    inner class TrailerViewHolder(val binding: ItemTrailersBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(trailer: Result) {
            binding.trailer = trailer
            binding.executePendingBindings()
        }
    }
}