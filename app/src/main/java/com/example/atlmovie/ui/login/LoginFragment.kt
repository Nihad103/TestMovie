package com.example.atlmovie.ui.login

import android.content.Context
import android.widget.Toast
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.atlmovie.R
import com.example.atlmovie.base.BaseFragment
import com.example.atlmovie.databinding.FragmentLoginBinding
import com.example.atlmovie.utils.Prefs
import com.example.atlmovie.utils.clearError
import com.example.atlmovie.utils.showError
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : BaseFragment<FragmentLoginBinding>(
    FragmentLoginBinding::inflate
) {
    private val viewModel: LoginViewModel by viewModel()

    override fun onViewCreateFinish() {
        observes()

        binding.ivBack.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.btnSignUp.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)
        }
        binding.btnSignIn.setOnClickListener {
           setBoxColors()
        }

    }

    private fun observes() {
        viewModel.isSuccess.observe(viewLifecycleOwner) {
            if (it) {
                if (binding.cbRememberMe.isChecked) {
                    Prefs.setSavedLogin(requireContext(), true)
                }
                Prefs.setUserEmail(requireContext(), binding.etEmail.text.toString().trim())

                val navOptions = NavOptions.Builder()
                    .setPopUpTo(R.id.loginFragment, true)
                    .build()

                findNavController().navigate(
                    R.id.action_loginFragment_to_homeFragment,
                    null,
                    navOptions
                )
            }
        }
        viewModel.isError.observe(viewLifecycleOwner) { error ->
            when (error) {
                "email" -> {
                    binding.tilEmail.showError("Email tapilmadi!", requireContext())
                    binding.tilPassword.clearError(requireContext())
                }
                "password" -> {
                    binding.tilPassword.showError("Sifre duzgun deyil!", requireContext())
                }
                else -> Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setBoxColors() {
        val email = binding.etEmail.text.toString().trim()
        val password = binding.etPassword.text.toString().trim()

        var hasError = false

        if (email.isEmpty()) {
            binding.tilEmail.showError("Email bos qoymaq olmaz!", requireContext())
            hasError = true
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.tilEmail.showError("Email duzgun formatda deyil!", requireContext())
            hasError = true
        } else {
            binding.tilEmail.clearError(requireContext())
        }

        if (password.isEmpty()) {
            binding.tilPassword.showError("Sifre bos qoymaq olmaz!", requireContext())
            hasError = true
        } else if (password.length < 6) {
            binding.tilPassword.showError("Minimum 6 simvol!", requireContext())
            hasError = true
        } else {
            binding.tilPassword.clearError(requireContext())
        }

        if (!hasError) {
            viewModel.login(email, password)
        }
    }
}