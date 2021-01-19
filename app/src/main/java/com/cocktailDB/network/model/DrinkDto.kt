package com.cocktailDB.network.model

import com.google.gson.annotations.SerializedName

data class DrinkDto(
    @SerializedName("idDrink")
    val idDrink: String? = null,
    @SerializedName("strDrink")
    val strDrink: String? = null,
    @SerializedName("strDrinkThumb")
    val strDrinkThumb: String? = null
)