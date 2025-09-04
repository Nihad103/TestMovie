package com.example.atlmovie.utils

import android.util.Log
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.atlmovie.R

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

fun View.visible(){
    this.visibility=View.VISIBLE
}
fun View.gone(){
    this.visibility=View.GONE
}
