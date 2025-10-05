package com.example.atlmovie.ui.profile.cards

import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.atlmovie.R
import com.example.atlmovie.base.BaseFragment
import com.example.atlmovie.databinding.FragmentSubscribeToPremiumBinding
import com.example.atlmovie.utils.gone
import com.example.atlmovie.utils.visible
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SubscribeToPremiumFragment : BaseFragment<FragmentSubscribeToPremiumBinding>(
    FragmentSubscribeToPremiumBinding::inflate
) {
    override fun onViewCreateFinish() {
        binding.ivBack.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.cv1.setOnClickListener {
            binding.cv1.isEnabled = false
            binding.cv2.isEnabled = false

            lifecycleScope.launch {
                binding.animationView22.visible()
                delay(1500)
                binding.animationView22.gone()
                findNavController().navigate(R.id.action_subscribeToPremiumFragment_to_paymentMethodFragment)
                binding.cv1.isEnabled = true
                binding.cv2.isEnabled = true

            }
        }

        binding.cv2.setOnClickListener {
            binding.cv1.isEnabled = false
            binding.cv2.isEnabled = false

            lifecycleScope.launch {
                binding.animationView22.visible()
                delay(1500)
                binding.animationView22.gone()
                findNavController().navigate(R.id.action_subscribeToPremiumFragment_to_paymentMethodFragment)
                binding.cv1.isEnabled = true
                binding.cv2.isEnabled = true

            }
        }
    }
}