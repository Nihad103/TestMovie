package com.example.atlmovie.ui.profile.cards

import android.util.Log
import android.view.LayoutInflater
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.atlmovie.R
import com.example.atlmovie.base.BaseFragment
import com.example.atlmovie.databinding.FragmentPaymentMethodBinding
import com.example.atlmovie.model.CardModel
import com.example.atlmovie.ui.adapter.payment.CardAdapter
import com.example.atlmovie.ui.adapter.payment.PaymentAdapter
import com.example.atlmovie.utils.maskCardNumberGrouped
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class PaymentMethodFragment : BaseFragment<FragmentPaymentMethodBinding>(
    FragmentPaymentMethodBinding::inflate
) {

    private val cardAdapter = CardAdapter()
    val adapter = PaymentAdapter()
    private val viewModel: PaymentViewModel by viewModel()
    val args: PaymentMethodFragmentArgs by navArgs()

    override fun onViewCreateFinish() {
        binding.ivBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.rv2.adapter = cardAdapter
        observe()

        binding.btnAddNewCard.setOnClickListener {
                val navController = findNavController()
                if (navController.currentDestination?.id == R.id.paymentMethodFragment) {
                    val action = PaymentMethodFragmentDirections
                        .actionPaymentMethodFragmentToAddNewFragment()
                    navController.navigate(action)
                }
        }

        adapter.onSelectClickListener = { payment ->
            viewModel.selectPayment(payment)
            cardAdapter.clearSelection()
        }

        cardAdapter.onSelectClickListener = { card ->
            viewModel.selectCard(card)
            adapter.clearSelection()
        }

        binding.btnContinue.setOnClickListener {
            lifecycleScope.launch {
                val navController = findNavController()
                if (navController.currentDestination?.id != R.id.paymentMethodFragment) {
                    return@launch
                }

                when {
                    viewModel.selectedCard != null -> {
                        val card = viewModel.selectedCard!!
                        val maskedNumber = maskCardNumberGrouped(card.cardNumber)
                        val action = PaymentMethodFragmentDirections
                            .actionPaymentMethodFragmentToReviewSummaryFragment(
                                maskedNumber,
                                card.cardImage,
                                card.cardName
                            )
                        findNavController().navigate(action)
                    }
                    viewModel.selectedPayment != null -> {
                        val payment = viewModel.selectedPayment!!
                        val action = PaymentMethodFragmentDirections
                            .actionPaymentMethodFragmentToReviewSummaryFragment(
                                cardNumber = "",
                                cardImage = payment.image,
                                paymentName = payment.title
                            )
                        findNavController().navigate(action)
                    }
                    else -> {
                        Toast.makeText(
                            requireContext(),
                            "Card or Payment not selected!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }

        cardAdapter.onItemClickListener = { cardId ->
            val dialogView =
                LayoutInflater.from(requireContext()).inflate(R.layout.alert_dialog, null)
            val alertDialog = MaterialAlertDialogBuilder(requireContext())
                .setView(dialogView)
                .create()

            val titleTextView = dialogView.findViewById<TextView>(R.id.tvLogoutTitle)
            val messageTextView = dialogView.findViewById<TextView>(R.id.tvLogoutMessage)
            val noButton = dialogView.findViewById<TextView>(R.id.btnCancel)
            val yesButton = dialogView.findViewById<TextView>(R.id.btnLogout)
            titleTextView.text = "Delete Card"
            messageTextView.text = "Are you sure you want to delete this Card?"
            noButton.setOnClickListener {
                alertDialog.dismiss()
            }
            yesButton.setOnClickListener {
                viewModel.deleteCard(cardId.id)
                alertDialog.dismiss()
            }
            alertDialog.show()
        }
    }

    private fun observe() {
        viewModel.cards.observe(viewLifecycleOwner) {
            val defaultCards = listOf(
                CardModel(
                    id = -1,
                    cardName = "PayPal",
                    cardNumber = "",
                    cardImage = R.drawable.paypal,
                    selected = false
                ),
                CardModel(
                    id = -2,
                    cardName = "Google Pay",
                    cardNumber = "",
                    cardImage = R.drawable.google,
                    selected = false
                ),
                CardModel(
                    id = -3,
                    cardName = "Apple Pay",
                    cardNumber = "",
                    cardImage = R.drawable.apple,
                    selected = false
                )
            )
            val allCards = defaultCards + it
            cardAdapter.updateList(allCards)
            Log.d("CARD_DEBUG", "List size: ${it.size}")
        }
        viewModel.getCards()
    }
}