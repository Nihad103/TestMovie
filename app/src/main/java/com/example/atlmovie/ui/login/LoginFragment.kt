package com.example.atlmovie.ui.login

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.atlmovie.R
import com.example.atlmovie.base.BaseFragment
import com.example.atlmovie.databinding.FragmentLoginBinding

class LoginFragment : BaseFragment<FragmentLoginBinding>(
    FragmentLoginBinding::inflate
) {
    private val viewModel by viewModels<LoginViewModel>()


    override fun onViewCreateFinish() {
        observes()
        binding.ivBack.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.btnSignUp.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)
        }
        binding.btnSignIn.setOnClickListener {
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                viewModel.login(email, password)
            } else {
                Toast.makeText(requireContext(), "Bos qoymaq olmaz!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun observes() {
        viewModel.isSuccess.observe(viewLifecycleOwner) {
            if (it) {
                saveLoginData()
                findNavController().navigate(
                    R.id.action_loginFragment_to_homeFragment,
                    null,
                    NavOptions.Builder()
                        .setPopUpTo(findNavController().graph.startDestinationId, true)
                        .build()
                )
            }
        }
        viewModel.isError.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    private fun saveLoginData() {
        val sharedPref = requireContext().getSharedPreferences("save_login", Context.MODE_PRIVATE)

        sharedPref.edit().putBoolean("saved_data", true).apply()
    }

}