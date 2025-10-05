package com.example.atlmovie.ui.register

import android.widget.Toast
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.atlmovie.R
import com.example.atlmovie.base.BaseFragment
import com.example.atlmovie.databinding.FragmentSignUpBinding
import com.example.atlmovie.utils.Prefs
import com.example.atlmovie.utils.clearError
import com.example.atlmovie.utils.showError
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignUpFragment : BaseFragment<FragmentSignUpBinding>(
    FragmentSignUpBinding::inflate
) {

    private val viewModel: SignUpViewModel by viewModel()

    override fun onViewCreateFinish() {
        observes()

        binding.ivBack.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.btnSignIn.setOnClickListener {
            findNavController().navigate(R.id.action_signUpFragment_to_loginFragment)
        }
        binding.btnSignUp.setOnClickListener {
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
                    .setPopUpTo(R.id.signUpFragment, true)
                    .build()

                findNavController().navigate(
                    R.id.action_signUpFragment_to_homeFragment,
                    null,
                    navOptions
                )
            }
        }
        viewModel.isError.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
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
            viewModel.register(email, password)
        }
    }
}