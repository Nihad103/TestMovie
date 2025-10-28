package com.example.atlmovie.ui.adapter.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.atlmovie.R
import com.example.atlmovie.databinding.ItemPeopleBinding
import com.example.atlmovie.model.detail.PeopleModel

class PeopleAdapter : Adapter<PeopleAdapter.PeopleViewHolder>() {

    private val itemsPeople = listOf(
        PeopleModel("Shawn Levy", "Director", R.drawable.people),
        PeopleModel("Shawn Levy", "Director", R.drawable.people),
        PeopleModel("Shawn Levy", "Director", R.drawable.people),
        PeopleModel("Shawn Levy", "Director", R.drawable.people),
        PeopleModel("Shawn Levy", "Director", R.drawable.people),
        PeopleModel("Shawn Levy", "Director", R.drawable.people),
        PeopleModel("Shawn Levy", "Director", R.drawable.people),
        PeopleModel("Shawn Levy", "Director", R.drawable.people),
        PeopleModel("Shawn Levy", "Director", R.drawable.people),
        PeopleModel("Shawn Levy", "Director", R.drawable.people),
        PeopleModel("Shawn Levy", "Director", R.drawable.people),
    )
    inner class PeopleViewHolder(private val itemPeopleBinding: ItemPeopleBinding) : ViewHolder(itemPeopleBinding.root) {
        fun bind(item: PeopleModel) {
            itemPeopleBinding.tvPeopleName.text = item.name
            itemPeopleBinding.tvWork.text = item.work
            itemPeopleBinding.ivPeople.setImageResource(item.image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeopleViewHolder {
        val view = ItemPeopleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PeopleViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itemsPeople.size
    }

    override fun onBindViewHolder(holder: PeopleViewHolder, position: Int) {
        val itemPeople = itemsPeople[position]
        holder.bind(itemPeople)

    }
}