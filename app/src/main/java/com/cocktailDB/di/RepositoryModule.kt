package com.cocktailDB.di

import com.cocktailDB.network.DrinkService
import com.cocktailDB.network.model.DrinkDtoMapper
import com.cocktailDB.network.model.DrinksCategoryDtoMapper
import com.cocktailDB.repository.DrinkRepository
import com.cocktailDB.repository.DrinkRepositoryImpl
import com.cocktailDB.room.DrinkCategoriesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun provideDrinkRepository(
            drinkService: DrinkService,
            drinkMapper: DrinkDtoMapper,
            drinksCategoryMapper: DrinksCategoryDtoMapper,
            drinkCategoriesDao: DrinkCategoriesDao,
    ): DrinkRepository {
        return DrinkRepositoryImpl(
                drinkService = drinkService,
                drinkMapper = drinkMapper,
                drinksCategoryMapper = drinksCategoryMapper,
                drinkCategoriesDao = drinkCategoriesDao,
        )
    }

}