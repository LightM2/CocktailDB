package com.cocktailDB.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.cocktailDB.room.model.DrinkCategoryEntity

@Database(entities = [DrinkCategoryEntity::class], version = 1)
abstract class DrinkCategoriesDatabase: RoomDatabase() {
    abstract fun drinkCategoriesDao(): DrinkCategoriesDao

    companion object{
        val DATABASE_NAME: String = "drink_categories_database"
    }

}