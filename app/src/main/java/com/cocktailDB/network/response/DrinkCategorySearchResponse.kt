package com.cocktailDB.network.response

import com.cocktailDB.network.model.DrinkDto
import com.cocktailDB.network.model.DrinksCategoryDto
import com.google.gson.annotations.SerializedName

data class DrinkCategorySearchResponse (
    @SerializedName("drinks")
    var drinkCategories: List<DrinksCategoryDto>
)