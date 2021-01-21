package com.cocktailDB.repository

import android.util.Log
import com.cocktailDB.domain.model.Drink
import com.cocktailDB.domain.model.DrinkCategory
import com.cocktailDB.network.DrinkService
import com.cocktailDB.network.model.DrinkDtoMapper
import com.cocktailDB.network.model.DrinksCategoryDtoMapper
import com.cocktailDB.room.DrinkCategoriesDao

class DrinkRepositoryImpl(
        private val drinkService: DrinkService,
        private val drinkMapper: DrinkDtoMapper,
        private val drinksCategoryMapper: DrinksCategoryDtoMapper,
        private val drinkCategoriesDao: DrinkCategoriesDao
): DrinkRepository {
    override suspend fun getDrinksFromNetwork(category: String): List<Drink>? {
        var drinks: List<Drink>? = null
        try {
            drinks = drinkMapper.toDomainList(drinkService.getDrinksFromNetwork(category).drinks)
            Log.d("Network get drinks", "Success")
        }catch (e: Exception){
            Log.d("Network get drinks", "Exception - $e")
        }
        return drinks
    }

    override suspend fun getDrinkCategoriesFromNetwork(category: String): List<DrinkCategory>? {
        var drinkCategories: List<DrinkCategory>? = null
        try {
            drinkCategories = drinksCategoryMapper.toDomainList(drinkService.getDrinkCategoriesFromNetwork(category).drinkCategories)
            Log.d("Network get drinkCategories", "Success")
        }catch (e: Exception){
            Log.d("Network get drinkCategories", "Exception - $e")
        }
        return drinkCategories
    }

    override suspend fun getDrinkCategoriesFromDB(): List<DrinkCategory>? {
        var drinkCategories: List<DrinkCategory>? = null

        try{
            drinkCategories = drinkCategoriesDao.get()
            Log.d("Room get drink Categories", "Success  $drinkCategories")

        }catch (e: Exception){
            Log.d("Room get drink Categories", "Exception $e")
        }

        return drinkCategories
    }

    override suspend fun setDrinkCategoriesToDB(drinkCategories: List<DrinkCategory>){

        try {
            drinkCategoriesDao.insert(drinkCategories)
            Log.d("Room set drink Categories", "Success")

        }catch (e: Exception){
            Log.d("Room set drink Categories", "Exception $e")
        }

    }

    override suspend fun updateDrinkCategoriesInDB(drinkCategories: List<DrinkCategory>) {
        try{
            drinkCategoriesDao.updateDrinkCategories(drinkCategories)
            Log.d("Room update Drink Categories", "Success")

        }catch (e: Exception){
            Log.d("Room update Drink Categories", "Exception $e")
        }
    }

}