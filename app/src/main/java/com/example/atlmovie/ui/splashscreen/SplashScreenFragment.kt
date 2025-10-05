package com.example.atlmovie.ui.splashscreen

import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.atlmovie.R
import com.example.atlmovie.base.BaseFragment
import com.example.atlmovie.databinding.FragmentSplashScreenBinding
import com.example.atlmovie.utils.Prefs
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashScreenFragment : BaseFragment<FragmentSplashScreenBinding>(
    FragmentSplashScreenBinding::inflate
) {
    override fun onViewCreateFinish() {
        lifecycleScope.launch {
            delay(2000)

            val isFirstLaunch = Prefs.isFirstLaunch(requireContext())
            val isSavedLogin = Prefs.isSavedLogin(requireContext())

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