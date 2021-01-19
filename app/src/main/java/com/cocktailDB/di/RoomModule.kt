package com.cocktailDB.di

import android.content.Context
import androidx.room.Room
import com.cocktailDB.room.DrinkCategoriesDao
import com.cocktailDB.room.DrinkCategoriesDatabase
import com.cocktailDB.room.model.DrinkCategoryEntityMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun provideDrinkCategoriesDatabase(@ApplicationContext context: Context): DrinkCategoriesDatabase {
        return Room.databaseBuilder(
                context,
                DrinkCategoriesDatabase::class.java,
                DrinkCategoriesDatabase.DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build()
    }


    @Singleton
    @Provides
    fun provideDrinkCategoriesDao(drinkCategoriesDatabase: DrinkCategoriesDatabase): DrinkCategoriesDao {
        return drinkCategoriesDatabase.drinkCategoriesDao()
    }

    @Singleton
    @Provides
    fun provideDrinkCategoryEntityMapper(): DrinkCategoryEntityMapper {
        return DrinkCategoryEntityMapper()
    }

}