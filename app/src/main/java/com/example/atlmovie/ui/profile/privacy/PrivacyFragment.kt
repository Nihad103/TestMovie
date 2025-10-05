package com.example.atlmovie.ui.profile.privacy

import androidx.navigation.fragment.findNavController
import com.example.atlmovie.base.BaseFragment
import com.example.atlmovie.databinding.FragmentPrivacyBinding

class PrivacyFragment : BaseFragment<FragmentPrivacyBinding>(
    FragmentPrivacyBinding::inflate
) {

    override fun onViewCreateFinish() {
        binding.ivBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }


}