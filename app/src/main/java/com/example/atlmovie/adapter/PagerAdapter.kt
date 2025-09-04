package com.example.atlmovie.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.atlmovie.databinding.ItemPagerBinding
import com.example.atlmovie.model.OnboardingModel
import com.example.atlmovie.ui.onboarding.OnboardingFragmentDirections

class PagerAdapter : RecyclerView.Adapter<PagerAdapter.PagerViewHolder>() {

    private val list = arrayListOf<OnboardingModel>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PagerViewHolder {
        val view = ItemPagerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PagerViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: PagerViewHolder,
        position: Int
    ) {
        val item = list[position]

        holder.itemPagerBinding.tvTitle.text = item.title
        holder.itemPagerBinding.tvSubTitle.text = item.subTitle
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun updateList(newList: List<OnboardingModel>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

    class PagerViewHolder(val itemPagerBinding: ItemPagerBinding) :
        RecyclerView.ViewHolder(itemPagerBinding.root)
}
