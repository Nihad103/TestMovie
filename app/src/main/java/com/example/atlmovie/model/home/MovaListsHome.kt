package com.example.atlmovie.model.home


import com.google.gson.annotations.SerializedName

data class MovaListsHome(
    @SerializedName("page")
    val page: Int,
    @SerializedName("dates")
    val dates: Dates? = null,
    @SerializedName("results")
    val movaResults: List<MovaResult>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)