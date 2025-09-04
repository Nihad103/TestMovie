package com.example.atlmovie.ui.splashscreen

import android.content.Context
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.atlmovie.R
import com.example.atlmovie.base.BaseFragment
import com.example.atlmovie.databinding.FragmentSplashScreenBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashScreenFragment : BaseFragment<FragmentSplashScreenBinding>(
    FragmentSplashScreenBinding::inflate
) {
    override fun onViewCreateFinish() {
        val prefs = requireContext().getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
        val isFirstLaunch = prefs.getBoolean("first_launch", true)

        lifecycleScope.launch {
            delay(2000)
            if (isFirstLaunch) {
                findNavController().navigate(R.id.action_splashScreenFragment_to_onboardingFragment2)
            } else {
                findNavController().navigate(R.id.action_splashScreenFragment_to_letsYouInFragment)
            }
        }
    }

}