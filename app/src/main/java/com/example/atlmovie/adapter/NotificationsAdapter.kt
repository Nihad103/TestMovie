package com.example.atlmovie.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.atlmovie.databinding.ItemNotificationsBinding
import com.example.atlmovie.model.home.MovaResult

class NotificationsAdapter(
    private val listener: OnMovieClickListener
) : Adapter<NotificationsAdapter.NotificationsViewHolder>(){

    private val itemsNotifications = arrayListOf<MovaResult>()

    inner class NotificationsViewHolder(private val itemNotificationsBinding: ItemNotificationsBinding)
        : ViewHolder(itemNotificationsBinding.root) {
        fun bind(item: MovaResult) {
            itemNotificationsBinding.film = item
            itemNotificationsBinding.root.setOnClickListener {
                listener.onMovieClick(item.id)
            }
        }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationsViewHolder {
        val view = ItemNotificationsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NotificationsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itemsNotifications.size
    }

    override fun onBindViewHolder(holder: NotificationsViewHolder, position: Int) {
        val itemNotifications = itemsNotifications[position]

        holder.bind(itemNotifications)
    }

    fun updateList(newList: ArrayList<MovaResult>) {
        itemsNotifications.clear()
        itemsNotifications.addAll(newList)
        notifyDataSetChanged()
    }
}