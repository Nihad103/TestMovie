package com.example.atlmovie.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.atlmovie.databinding.ItemNewReleasesBinding
import com.example.atlmovie.model.home.MovaResult

class NewReleasesAdapter(
    private val listener: OnMovieClickListener
) : Adapter<NewReleasesAdapter.NewReleasesViewHolder>() {

    private val itemsNewReleases = arrayListOf<MovaResult>()

    inner class NewReleasesViewHolder(private val itemNewReleasesBinding: ItemNewReleasesBinding) : RecyclerView.ViewHolder(itemNewReleasesBinding.root) {
        fun bind(item: MovaResult) {
            itemNewReleasesBinding.movaNewReleases = item
            itemNewReleasesBinding.root.setOnClickListener {
                listener.onMovieClick(item.id)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NewReleasesViewHolder {
        val view = ItemNewReleasesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewReleasesViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itemsNewReleases.take(10).size
    }

    override fun onBindViewHolder(holder: NewReleasesViewHolder, position: Int) {
        val itemNewRelease = itemsNewReleases[position]

        holder.bind(itemNewRelease)
    }

    fun updateList(newList: ArrayList<MovaResult>) {
        itemsNewReleases.clear()
        itemsNewReleases.addAll(newList)
        notifyDataSetChanged()
    }

}