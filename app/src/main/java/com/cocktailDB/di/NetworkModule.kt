package com.cocktailDB.di

import com.cocktailDB.network.DrinkService
import com.cocktailDB.network.model.DrinkDtoMapper
import com.cocktailDB.network.model.DrinksCategoryDtoMapper
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideDrinkMapper(): DrinkDtoMapper {
        return DrinkDtoMapper()
    }

    @Singleton
    @Provides
    fun provideDrinkCategoryMapper(): DrinksCategoryDtoMapper {
        return DrinksCategoryDtoMapper()
    }

    @Singleton
    @Provides
    fun provideDrinkService(): DrinkService {
        return Retrofit.Builder()
                .baseUrl("https://www.thecocktaildb.com/api/json/v1/1/")
                //https://www.thecocktaildb.com/api/json/v1/1/filter.php?c=Ordinary%20Drink
                //https://www.thecocktaildb.com/api/json/v1/1/list.php?c=list
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .build()
                .create(DrinkService::class.java)
    }


}