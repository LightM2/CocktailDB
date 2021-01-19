package com.cocktailDB.repository

import android.util.Log
import com.cocktailDB.domain.model.Drink
import com.cocktailDB.domain.model.DrinkCategory
import com.cocktailDB.network.DrinkService
import com.cocktailDB.network.model.DrinkDtoMapper
import com.cocktailDB.network.model.DrinksCategoryDtoMapper
import com.cocktailDB.room.DrinkCategoriesDao
import com.cocktailDB.room.model.DrinkCategoryEntity
import com.cocktailDB.room.model.DrinkCategoryEntityMapper

class DrinkRepositoryImpl(
        private val drinkService: DrinkService,
        private val drinkMapper: DrinkDtoMapper,
        private val drinksCategoryMapper: DrinksCategoryDtoMapper,
        private val drinkCategoriesDao: DrinkCategoriesDao,
        private val drinkCategoryEntityMapper: DrinkCategoryEntityMapper
): DrinkRepository {
    override suspend fun getDrinksFromNetwork(category: String): List<Drink> {
        return drinkMapper.toDomainList(drinkService.getDrinksFromNetwork(category).drinks)
    }

    override suspend fun getDrinkCategoriesFromNetwork(category: String): List<DrinkCategory> {
        return drinksCategoryMapper.toDomainList(drinkService.getDrinkCategoriesFromNetwork(category).drinkCategories)
    }

    override suspend fun getDrinkCategoriesFromDB(): List<DrinkCategory>? {
        var drinkCategories: List<DrinkCategoryEntity>? = null

        try{
            drinkCategories = drinkCategoriesDao.get()
            Log.d("Room get drink Categories", "Success  $drinkCategories")

        }catch (e: Exception){
            Log.d("Room get drink Categories", "Exception $e")
        }

        return drinkCategories?.let { drinkCategoryEntityMapper.toDomainList(it) }
    }

    override suspend fun setDrinkCategoriesToDB(drinkCategories: List<DrinkCategory>){

        try {
            drinkCategoriesDao.insert(drinkCategoryEntityMapper.fromDomainList(drinkCategories))
            Log.d("Room set drink Categories", "Success")

        }catch (e: Exception){
            Log.d("Room set drink Categories", "Exception $e")
        }

    }

    override suspend fun updateDrinkCategoryInDB(drinkCategory: DrinkCategoryEntity) {
        try{
            drinkCategoriesDao.updateDrinkCategory(drinkCategory)
            Log.d("Room update Drink Category", "Success")

        }catch (e: Exception){
            Log.d("Room update Drink Category", "Exception $e")
        }
    }

}