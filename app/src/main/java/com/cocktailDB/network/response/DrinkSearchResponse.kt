package com.cocktailDB.network.response

import com.cocktailDB.network.model.DrinkDto
import com.google.gson.annotations.SerializedName

data class DrinkSearchResponse (
    @SerializedName("drinks")
    var drinks: List<DrinkDto>?
)