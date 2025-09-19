package com.example.atlmovie.utils

import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.atlmovie.R
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

fun ImageView.loadImageYoutube(key: String?){
    key?.let {
        val youtubeUrl = "https://img.youtube.com/vi/"+it+"/hqdefault.jpg"
        Glide.with(this.context)
            .load(youtubeUrl)
            .into(this)
        }
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


fun View.visible(){
    this.visibility=View.VISIBLE
}
fun View.gone(){
    this.visibility=View.GONE
}
