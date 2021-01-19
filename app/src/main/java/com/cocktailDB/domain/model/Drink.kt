package com.cocktailDB.domain.model

data class Drink(
    val id: String? = null,
    val drinkName: String? = null,
    val drinkPhoto: String? = null
): CategoryOrDrink {
    override fun getItemType(): Type = Type.DRINK
}