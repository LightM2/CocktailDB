package com.cocktailDB.room.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "drinkCategories")
data class DrinkCategoryEntity(
        @PrimaryKey(autoGenerate = true)
        var id: Int,

        @ColumnInfo(name = "drinkCategory")
        var drinkCategory: String?,

        @ColumnInfo(name = "check")
        var check: Boolean
)
