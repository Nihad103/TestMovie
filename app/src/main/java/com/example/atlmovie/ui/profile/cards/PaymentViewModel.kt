package com.example.atlmovie.ui.profile.cards

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.atlmovie.R
import com.example.atlmovie.model.CardModel
import com.example.atlmovie.model.PaymentModel
import com.example.atlmovie.service.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PaymentViewModel(
    private val repository: MovieRepository
) : ViewModel() {

    val cards = MutableLiveData<List<CardModel>>()
    var selectedCard: CardModel? = null
    var selectedPayment: PaymentModel? = null

    fun getCards() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getCards().collect {
                cards.postValue(it)
            }
        }
    }

//    fun addCard(name: String, number: String) {
//        viewModelScope.launch(Dispatchers.IO) {
//            repository.addCard(
//                CardModel(
//                    cardName = name, cardNumber = number.toString(),
//                    cardImage = R.drawable.mastercard,
//                    selected = false,)
//            )
//            getCards()
//        }
//    }

    fun selectCard(card: CardModel) {
        selectedCard = card
        selectedPayment = null
    }

    fun selectPayment(payment: PaymentModel) {
        selectedPayment = payment
        selectedCard = null
    }

    fun deleteCard(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteCard(id)
            getCards()
        }

    }
}