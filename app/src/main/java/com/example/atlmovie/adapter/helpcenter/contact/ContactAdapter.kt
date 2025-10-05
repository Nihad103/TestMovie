package com.example.atlmovie.adapter.helpcenter.contact

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.atlmovie.databinding.ItemContactBinding
import com.example.atlmovie.model.helpcenter.contact.Contact

class ContactAdapter : RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {

    val contactList = arrayListOf<Contact>()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ContactViewHolder {
        val view = ItemContactBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ContactViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ContactViewHolder,
        position: Int
    ) {
        val item = contactList[position]
        holder.itemContactBinding.contact = item
        holder.itemContactBinding.ivContact.setImageResource(item.icon)
    }

    override fun getItemCount(): Int {
        return contactList.size
    }

    fun updateList(list: List<Contact>) {
        contactList.clear()
        contactList.addAll(list)
        notifyDataSetChanged()
    }

    class ContactViewHolder(val itemContactBinding: ItemContactBinding) :
        RecyclerView.ViewHolder(itemContactBinding.root) {
    }
}