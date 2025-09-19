package com.example.atlmovie.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.atlmovie.R

@BindingAdapter("load_url")
fun loadImage(imageView: ImageView, url: String?) {
    if (url.isNullOrEmpty()) {
        imageView.setImageResource(R.drawable.placeholder)
    } else {
        imageView.loadImageUrl(url)
    }
}

@BindingAdapter("load_url_youtube")
fun ImageView.loadYutube(key: String?) {
    val url = "https://img.youtube.com/vi/$key/hqdefault.jpg"
    Glide.with(context)
        .load(url)
        .placeholder(R.drawable.placeholder)
        .into(this)
}