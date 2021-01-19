package com.cocktailDB.domain.model

data class DrinkCategory(
    val drinksCategory: String? = null,
    val check: Boolean = true
): CategoryOrDrink {
    override fun getItemType(): Type = Type.CATEGORY
}