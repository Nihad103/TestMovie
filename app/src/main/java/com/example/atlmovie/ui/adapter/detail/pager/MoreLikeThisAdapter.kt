package com.example.atlmovie.ui.adapter.detail.pager

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.atlmovie.databinding.ItemMoreLikeThisBinding
import com.example.atlmovie.model.home.MovaResult
import com.example.atlmovie.ui.adapter.OnMovieClickListener

class MoreLikeThisAdapter(
    private val listener: OnMovieClickListener
) : RecyclerView.Adapter<MoreLikeThisAdapter.MoreLikeThisViewHolder>() {

    private val itemsPopular = arrayListOf<MovaResult>()

    inner class MoreLikeThisViewHolder(private val itemMoreLikeThisBinding: ItemMoreLikeThisBinding) :
        RecyclerView.ViewHolder(itemMoreLikeThisBinding.root) {
        fun bind(item: MovaResult) {
            itemMoreLikeThisBinding.mova = item
            itemMoreLikeThisBinding.root.setOnClickListener {
                listener.onMovieClick(item.id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoreLikeThisViewHolder {
        val view =
            ItemMoreLikeThisBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoreLikeThisViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itemsPopular.size
    }

    override fun onBindViewHolder(holder: MoreLikeThisViewHolder, position: Int) {
        val itemPopular = itemsPopular[position]

        holder.bind(itemPopular)
    }

    fun updateList(newList: ArrayList<MovaResult>) {
        itemsPopular.clear()
        itemsPopular.addAll(newList)
        notifyDataSetChanged()
    }
}