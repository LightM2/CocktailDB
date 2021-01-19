package com.cocktailDB.network.model

import com.google.gson.annotations.SerializedName

data class DrinksCategoryDto(
    @SerializedName("strCategory")
    val strCategory: String? = null
)