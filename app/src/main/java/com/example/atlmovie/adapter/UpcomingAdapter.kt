package com.example.atlmovie.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.atlmovie.databinding.ItemUpcomingBinding
import com.example.atlmovie.model.home.MovaResult

class UpcomingAdapter(
    private val listener: OnMovieClickListener
) : RecyclerView.Adapter<UpcomingAdapter.UpcomingViewHolder>() {

    private val itemsUpcoming = arrayListOf<MovaResult>()

    inner class UpcomingViewHolder(private val itemUpcomingBinding: ItemUpcomingBinding) : RecyclerView.ViewHolder(itemUpcomingBinding.root) {
        fun bind(item: MovaResult) {
            itemUpcomingBinding.movaUpcoming = item
            itemUpcomingBinding.root.setOnClickListener {
                listener.onMovieClick(item.id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpcomingViewHolder {
        val view = ItemUpcomingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UpcomingViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itemsUpcoming.take(10).size
    }

    override fun onBindViewHolder(holder: UpcomingViewHolder, position: Int) {
        val itemUpcoming = itemsUpcoming[position]

        holder.bind(itemUpcoming)
    }

    fun updateList(newList: ArrayList<MovaResult>) {
        itemsUpcoming.clear()
        itemsUpcoming.addAll(newList)
        notifyDataSetChanged()
    }
}