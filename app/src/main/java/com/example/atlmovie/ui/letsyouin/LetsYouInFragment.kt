package com.example.atlmovie.ui.letsyouin

import android.app.AlertDialog
import android.content.Context
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.atlmovie.R
import com.example.atlmovie.base.BaseFragment
import com.example.atlmovie.databinding.FragmentLetsYouInBinding

class LetsYouInFragment : BaseFragment<FragmentLetsYouInBinding>(
    FragmentLetsYouInBinding::inflate
) {
    override fun onViewCreateFinish() {
        getLoginData()
        binding.btnSignIn.setOnClickListener {
            findNavController().navigate(R.id.action_letsYouInFragment_to_loginFragment)
        }
        binding.btnSignUp.setOnClickListener {
            findNavController().navigate(R.id.action_letsYouInFragment_to_signUpFragment)
        }
        binding.ivBack.setOnClickListener {
            AlertDialog.Builder(requireContext())
                .setTitle("Appden cixilsinmi?")
                .setPositiveButton("Yes") { _, _ ->
                    requireActivity().finishAffinity()
                }
                .setNegativeButton("No") { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        }
    }

    private fun getLoginData() {
        val sharedPref = requireContext().getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
        val isAuth = sharedPref.getBoolean("saved_login", false)

        if (isAuth) {
            val navOptions = NavOptions.Builder()
                .setPopUpTo(R.id.letsYouInFragment, true)
                .build()
            findNavController().navigate(
                R.id.action_letsYouInFragment_to_homeFragment,
                null,
                navOptions
            )
        }
    }

}