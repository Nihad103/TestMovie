package com.example.atlmovie.model.openyoutube


import com.google.gson.annotations.SerializedName

data class MovaVideos(
    @SerializedName("id")
    val id: Int,
    @SerializedName("results")
    val results: List<Result>
)