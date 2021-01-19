package com.cocktailDB.repository

import com.cocktailDB.domain.model.Drink
import com.cocktailDB.domain.model.DrinkCategory
import com.cocktailDB.room.model.DrinkCategoryEntity

interface DrinkRepository {
    suspend fun getDrinksFromNetwork(category: String) : List<Drink>

    suspend fun getDrinkCategoriesFromNetwork(category: String) : List<DrinkCategory>

    suspend fun getDrinkCategoriesFromDB() : List<DrinkCategory>?

    suspend fun setDrinkCategoriesToDB(drinkCategories: List<DrinkCategory>)

    suspend fun updateDrinkCategoryInDB(drinkCategory: DrinkCategoryEntity)
}