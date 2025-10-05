package com.example.atlmovie.utils

import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.atlmovie.R
import com.example.atlmovie.model.detail.MovaDetail
import com.example.atlmovie.model.download.DownloadEntity
import com.example.atlmovie.model.mylist.MyListEntity
import com.google.android.material.textfield.TextInputLayout

fun ImageView.loadImageUrl(url: String?) {
    val fullUrl = if (!url.isNullOrEmpty()) {
        "https://image.tmdb.org/t/p/original$url"
    } else null
    Log.d("PosterPath", "Full URL: $fullUrl")

    Glide.with(this.context)
        .load(fullUrl)
        .placeholder(R.drawable.placeholder)
        .error(R.drawable.placeholder)
        .into(this)
}

fun TextInputLayout.showError(message: String, context: android.content.Context) {
    this.error = message
    this.setBoxBackgroundColorResource(R.color.red_218)
    this.setStartIconTintList(ContextCompat.getColorStateList(context, R.color.red_21))
    if (this.endIconMode == TextInputLayout.END_ICON_PASSWORD_TOGGLE) {
        this.isEndIconVisible = true
    }
    this.setEndIconTintList(ContextCompat.getColorStateList(context, R.color.red_21))
}

fun TextInputLayout.clearError(context: android.content.Context) {
    this.error = null
    this.setBoxBackgroundColorResource(R.color.grey_fa)
    this.setStartIconTintList(ContextCompat.getColorStateList(context, R.color.grey_21))
    if (this.endIconMode == TextInputLayout.END_ICON_PASSWORD_TOGGLE) {
        this.isEndIconVisible = true
    }
    this.setEndIconTintList(ContextCompat.getColorStateList(context, R.color.grey_21))
}

fun MovaDetail.toMyListEntity(): MyListEntity {
    return MyListEntity(
        id = this.id,
        adult = this.adult,
        backdropPath = this.backdropPath,
        originalLanguage = this.originalLanguage,
        originalTitle = this.originalTitle,
        overview = this.overview,
        popularity = this.popularity,
        posterPath = this.posterPath,
        releaseDate = this.releaseDate,
        title = this.title,
        video = this.video,
        voteAverage = this.voteAverage,
        voteCount = this.voteCount
    )
}

fun MovaDetail.toDownloadEntity(): DownloadEntity {
    return DownloadEntity(
        id = id,
        adult = adult,
        backdropPath = backdropPath,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath,
        releaseDate = releaseDate,
        title = title,
        video = video,
        voteAverage = voteAverage,
        voteCount = voteCount
    )
}

fun View.visible(){
    this.visibility=View.VISIBLE
}
fun View.gone(){
    this.visibility=View.GONE
}


fun maskCardNumberGrouped(cardNumber: String): String {
    val visibleCount = 4
    val digitsOnly = cardNumber.replace(" ", "")
    val maskedBuilder = StringBuilder()

    for (i in digitsOnly.indices) {
        maskedBuilder.append(
            if (i < digitsOnly.length - visibleCount) '*' else digitsOnly[i]
        )
    }
    return maskedBuilder.toString().chunked(4).joinToString(" ")
}