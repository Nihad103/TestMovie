package com.example.atlmovie.ui.profile.notification

import androidx.navigation.fragment.findNavController
import com.example.atlmovie.base.BaseFragment
import com.example.atlmovie.databinding.FragmentNotificationSettingsBinding

class NotificationSettingsFragment : BaseFragment<FragmentNotificationSettingsBinding>(
    FragmentNotificationSettingsBinding::inflate
) {

    override fun onViewCreateFinish() {
        binding.ivBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}