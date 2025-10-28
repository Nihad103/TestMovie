package com.example.atlmovie.ui.adapter.helpcenter.faq

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.atlmovie.databinding.ItemFaqBinding
import com.example.atlmovie.model.helpcenter.faq.Faq

class FaqAdapter :RecyclerView.Adapter<FaqAdapter.FaqViewHolder>(){

    val faqList = arrayListOf<Faq>()

    var onClick: ((ItemFaqBinding, Faq, Int) -> Unit)? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FaqViewHolder {
        val itemFaqBinding= ItemFaqBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return FaqViewHolder(itemFaqBinding)
    }

    override fun onBindViewHolder(
        holder: FaqViewHolder,
        position: Int
    ) {
        val item =faqList[position]
        holder.itemFaqBinding.faq = item

        holder.itemFaqBinding.tvAnswer.visibility = if (item.isExpanded) View.VISIBLE  else View.GONE

        holder.itemFaqBinding.faqCard.setOnClickListener {
            item.isExpanded = !item.isExpanded
            holder.itemFaqBinding.tvAnswer.visibility = if (item.isExpanded) View.VISIBLE else View.GONE
            onClick?.invoke(holder.itemFaqBinding, item, position)
        }
    }

    override fun getItemCount(): Int {
        return faqList.size
    }
    fun updateList(list:List<Faq>){
        faqList.clear()
        faqList.addAll(list)
        notifyDataSetChanged()
    }

    class FaqViewHolder( val itemFaqBinding: ItemFaqBinding) : RecyclerView.ViewHolder(itemFaqBinding.root)
}