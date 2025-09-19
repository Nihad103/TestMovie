package com.example.atlmovie.adapter.pager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.atlmovie.ui.detail.morelikethis.MoreLikeThisFragment
import com.example.atlmovie.ui.detail.trailers.TrailersFragment

class DetailPagerAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle,
    private val movieId: Int
) : FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> TrailersFragment.newInstance(movieId)
            1 -> MoreLikeThisFragment()
            else -> Fragment()
        }
    }
}