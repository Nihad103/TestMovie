package com.example.atlmovie.ui.onboarding

import android.content.Context
import android.view.View
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.atlmovie.R
import com.example.atlmovie.adapter.PagerAdapter
import com.example.atlmovie.base.BaseFragment
import com.example.atlmovie.databinding.FragmentOnboardingBinding
import com.example.atlmovie.model.OnboardingModel

class OnboardingFragment : BaseFragment<FragmentOnboardingBinding>(
    FragmentOnboardingBinding::inflate
) {
    private val pagerAdapter = PagerAdapter()

    override fun onViewCreateFinish() {
        binding.viewPager.adapter = pagerAdapter
        binding.springDotsIndicator.attachTo(binding.viewPager)

        val list = ArrayList<OnboardingModel>()
        list.add(OnboardingModel("Welcome Mova", "bvhirivrbivnrnirnvnivrn"))
        list.add(OnboardingModel("Welcome Mova 2", "kmnvfelovmlomvv vev rv frre"))
        list.add(OnboardingModel("Welcome Mova 3", "rfcr rfve v ve grtr"))


        pagerAdapter.updateList(list)

        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (position == list.size - 1) {
                    binding.btnNext.visibility = View.VISIBLE
                } else {
                    binding.btnNext.visibility = View.GONE
                }
            }
        })

        binding.btnNext.setOnClickListener {
            val prefs = requireContext().getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
            prefs.edit().putBoolean("first_launch", false).apply()

            findNavController().navigate(R.id.action_onboardingFragment_to_letsYouInFragment)
        }
    }
}