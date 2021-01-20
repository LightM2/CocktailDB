package com.cocktailDB.room

import androidx.room.*
import com.cocktailDB.domain.model.DrinkCategory

@Dao
interface DrinkCategoriesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(drinkCategories: List<DrinkCategory>)

    @Query("SELECT * FROM tableNameDrinkCategories")
    suspend fun get(): List<DrinkCategory>

    @Update
    suspend fun updateDrinkCategories(drinkCategories: List<DrinkCategory>)


}