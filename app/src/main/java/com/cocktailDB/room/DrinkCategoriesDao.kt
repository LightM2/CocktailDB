package com.cocktailDB.room

import androidx.room.*
import com.cocktailDB.room.model.DrinkCategoryEntity

@Dao
interface DrinkCategoriesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(drinkCategories: List<DrinkCategoryEntity>): Long

    @Query("SELECT * FROM drinkCategories")
    suspend fun get(): List<DrinkCategoryEntity>

    @Update
    suspend fun updateDrinkCategory(drinkCategory: DrinkCategoryEntity)


}