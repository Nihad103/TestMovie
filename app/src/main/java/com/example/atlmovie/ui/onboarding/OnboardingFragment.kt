package com.example.atlmovie.ui.onboarding

import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.atlmovie.R
import com.example.atlmovie.base.BaseFragment
import com.example.atlmovie.databinding.FragmentOnboardingBinding
import com.example.atlmovie.model.onboarding.OnboardingModel
import com.example.atlmovie.ui.adapter.onboarding.PagerAdapter
import com.example.atlmovie.utils.Prefs

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
                binding.btnNext.visibility =
                    if (binding.viewPager.currentItem == list.size - 1) View.VISIBLE else View.GONE
            }
        })

        binding.btnNext.setOnClickListener {
            Prefs.setFirstLaunch(requireContext(), false)

            findNavController().navigate(R.id.action_onboardingFragment_to_letsYouInFragment)
        }
    }
}