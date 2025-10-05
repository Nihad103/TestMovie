package com.example.atlmovie.ui.profile.cards

import android.app.AlertDialog
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.atlmovie.R
import com.example.atlmovie.base.BaseFragment
import com.example.atlmovie.databinding.FragmentReviewSummaryBinding
import com.example.atlmovie.utils.gone
import com.example.atlmovie.utils.visible
import com.google.android.material.button.MaterialButton
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ReviewSummaryFragment : BaseFragment<FragmentReviewSummaryBinding>(
    FragmentReviewSummaryBinding::inflate
) {

    private val args: ReviewSummaryFragmentArgs by navArgs()

    override fun onViewCreateFinish() {
        binding.tvCardNumber.text = when {
            args.cardNumber.isNotEmpty() -> args.cardNumber
            args.paymentName.isNotEmpty() -> args.paymentName
            else -> ""
        }

        if (args.paymentName == "Apple Pay") {
            val isNightMode = (requireContext().resources.configuration.uiMode
                    and android.content.res.Configuration.UI_MODE_NIGHT_MASK) ==
                    android.content.res.Configuration.UI_MODE_NIGHT_YES

            if (isNightMode) {
                binding.ivCard.setImageResource(R.drawable.applewhite)
            } else {
                binding.ivCard.setImageResource(R.drawable.apple)
            }
        } else {
            binding.ivCard.setImageResource(args.cardImage.toInt())
        }

        binding.ivBack.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.tvCardChange.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.button5confirmpayment.setOnClickListener {
            showCongratsDialog()
        }
    }

    private fun showCongratsDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_congrlations, null)

        val builder = AlertDialog.Builder(requireContext())
        builder.setView(dialogView)

        val dialog = builder.create()
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.setCancelable(false)
        dialog.show()

        dialogView.findViewById<MaterialButton>(R.id.btnOk).setOnClickListener {
            findNavController().navigate(ReviewSummaryFragmentDirections.actionReviewSummaryFragmentToProfileFragment())
            dialog.dismiss()
        }
    }

}