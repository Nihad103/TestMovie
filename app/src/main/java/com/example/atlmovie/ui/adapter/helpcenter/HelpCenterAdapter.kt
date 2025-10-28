package com.example.atlmovie.ui.adapter.helpcenter

import android.content.res.Resources
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.atlmovie.ui.profile.helpcenter.contactus.ContactUsFragment
import com.example.atlmovie.ui.profile.helpcenter.faq.FaqFragment

class HelpCenterAdapter(fragment: Fragment) :
    FragmentStateAdapter(fragment) {
    override fun createFragment(position: Int): Fragment {
        return when (position) {

            0 -> FaqFragment()
            1 -> ContactUsFragment()
            else -> throw Resources.NotFoundException("Position Not Found")
        }
    }

    override fun getItemCount(): Int {
        return 2
    }
}