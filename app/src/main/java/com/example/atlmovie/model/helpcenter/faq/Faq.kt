package com.example.atlmovie.model.helpcenter.faq

data class Faq(
    val question: String,
    val answer: String,
    var isExpanded: Boolean = false
)