package com.cocktailDB.network

import com.cocktailDB.network.response.DrinkCategorySearchResponse
import com.cocktailDB.network.response.DrinkSearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface DrinkService {
    @GET("filter.php")
    suspend fun getDrinksFromNetwork(
        @Query("c") category: String
    ): DrinkSearchResponse

    @GET("list.php")
    suspend fun getDrinkCategoriesFromNetwork(
        @Query("c") category: String
    ): DrinkCategorySearchResponse
}