package com.example.atlmovie.ui.profile.helpcenter

import androidx.navigation.fragment.findNavController
import com.example.atlmovie.adapter.helpcenter.HelpCenterAdapter
import com.example.atlmovie.base.BaseFragment
import com.example.atlmovie.databinding.FragmentHelpCenterBinding
import com.google.android.material.tabs.TabLayoutMediator

class HelpCenterFragment : BaseFragment<FragmentHelpCenterBinding>(
    FragmentHelpCenterBinding::inflate
) {

    private val pagerAdapter by lazy { HelpCenterAdapter(this) }

    override fun onViewCreateFinish() {
        binding.ivBack.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.helpPager.adapter = pagerAdapter

        setTablayout()
    }

    private fun setTablayout() {

        TabLayoutMediator(binding.tabLayout2, binding.helpPager) { tab, position ->
            when (position) {
                0 -> tab.text = "FAQ"
                1 -> tab.text = "Contact us"
            }
        }.attach()
    }

}