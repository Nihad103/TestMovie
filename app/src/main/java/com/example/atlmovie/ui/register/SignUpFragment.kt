package com.example.atlmovie.ui.register

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.atlmovie.R
import com.example.atlmovie.base.BaseFragment
import com.example.atlmovie.databinding.FragmentSignUpBinding

class SignUpFragment : BaseFragment<FragmentSignUpBinding>(
    FragmentSignUpBinding::inflate
) {

    private val viewModel by viewModels<SignUpViewModel>()


    override fun onViewCreateFinish() {
        observes()
        binding.ivBack.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.btnSignIn.setOnClickListener {
            findNavController().navigate(R.id.action_signUpFragment_to_loginFragment)
        }
        binding.btnSignUp.setOnClickListener {
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                viewModel.register(email, password)

            } else {
                Toast.makeText(requireContext(), "Bos qoymaq olmaz!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun observes() {
        viewModel.isSuccess.observe(viewLifecycleOwner) {
            if (it) {
                // Giriş məlumatlarını yaddaşa yaz
                val prefs = requireContext().getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
                if (binding.cbRememberMe.isChecked) {
                    prefs.edit().putBoolean("saved_login", true).apply()
                }

                val navOptions = NavOptions.Builder()
                    .setPopUpTo(R.id.signUpFragment, true)
                    .build()

                findNavController().navigate(
                    R.id.action_signUpFragment_to_homeFragment,
                    null,
                    navOptions
                )
            }
            viewModel.isError.observe(viewLifecycleOwner) {
                Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }
}