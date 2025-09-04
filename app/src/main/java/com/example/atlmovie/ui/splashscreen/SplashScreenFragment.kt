package com.example.atlmovie.ui.splashscreen

import android.content.Context
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavOptions
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
        lifecycleScope.launch {
            delay(2000)

            val prefs = requireContext().getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
            val isFirstLaunch = prefs.getBoolean("first_launch", true)
            val isSavedLogin = prefs.getBoolean("saved_login", false)

            val navController = findNavController()

            when {
                isSavedLogin -> {
                    val navOptions = NavOptions.Builder()
                        .setPopUpTo(R.id.splashScreenFragment, true)
                        .build()
                    navController.navigate(R.id.action_splashScreenFragment_to_homeFragment, null, navOptions)
                }
                isFirstLaunch -> {
                    val navOptions = NavOptions.Builder()
                        .setPopUpTo(R.id.splashScreenFragment, true)
                        .build()
                    navController.navigate(R.id.action_splashScreenFragment_to_onboardingFragment2, null, navOptions)
                }
                else -> {
                    val navOptions = NavOptions.Builder()
                        .setPopUpTo(R.id.splashScreenFragment, true)
                        .build()
                    navController.navigate(R.id.action_splashScreenFragment_to_letsYouInFragment, null, navOptions)
                }
            }
        }
    }
}