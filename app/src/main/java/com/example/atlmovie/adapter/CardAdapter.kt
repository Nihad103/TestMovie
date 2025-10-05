package com.example.atlmovie.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.atlmovie.R
import com.example.atlmovie.databinding.ItemCardsBinding
import com.example.atlmovie.model.CardModel
import com.example.atlmovie.utils.maskCardNumberGrouped

class CardAdapter : RecyclerView.Adapter<CardAdapter.CardViewHolder>()  {
    private val cardList = arrayListOf<CardModel>()
    private var selectedPosition = -1

    fun clearSelection() {
        selectedPosition = -1
        notifyDataSetChanged()
    }

    var onItemClickListener: ((CardModel) -> Unit)? = null
    var onSelectClickListener: ((CardModel) -> Unit)? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CardViewHolder {
        val view= ItemCardsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CardViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: CardViewHolder,
        position: Int
    ) {
        val item=cardList[position]

        holder.itemCardsBinding.tvBank.text = item.cardName
        when (item.cardName) {
            "PayPal" -> {
                holder.itemCardsBinding.tvBank.text = "PayPal"
                holder.itemCardsBinding.ivCards.setImageResource(R.drawable.paypal)
            }
            "Google Pay" -> {
                holder.itemCardsBinding.tvBank.text = "Google Pay"
                holder.itemCardsBinding.ivCards.setImageResource(R.drawable.google)
            }
            "Apple Pay" -> {
                holder.itemCardsBinding.tvBank.text = "Apple Pay"

                val isNightMode=(holder.itemView.context.resources.configuration.uiMode
                        and android.content.res.Configuration.UI_MODE_NIGHT_MASK)==android.content.res.Configuration.UI_MODE_NIGHT_YES
                if (isNightMode)
                    holder.itemCardsBinding.ivCards.setImageResource(R.drawable.applewhite)
                else{
                    holder.itemCardsBinding.ivCards.setImageResource(R.drawable.apple)
                }
            }
            else -> {

                holder.itemCardsBinding.tvBank.text = maskCardNumberGrouped(item.cardNumber)
                holder.itemCardsBinding.ivCards.setImageResource(R.drawable.mastercard)
            }
        }

        holder.itemCardsBinding.ivChecked.visibility=
            if (selectedPosition == position) View.VISIBLE
            else View.GONE

        holder.itemCardsBinding.ivChecked.setOnClickListener {
            onItemClickListener?.invoke(item)
        }
        holder.itemCardsBinding.materialCardView.setOnClickListener {
            selectedPosition = holder.bindingAdapterPosition
            notifyDataSetChanged()

            onSelectClickListener?.invoke(item)
        }

    }

    override fun getItemCount(): Int {
        return cardList.size
    }

    fun updateList(list:List<CardModel>){
        cardList.clear()
        cardList.addAll(list)
        notifyDataSetChanged()

    }

    class CardViewHolder(val itemCardsBinding: ItemCardsBinding) : RecyclerView.ViewHolder(itemCardsBinding.root)
}