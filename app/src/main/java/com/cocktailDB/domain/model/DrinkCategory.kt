package com.cocktailDB.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tableNameDrinkCategories")
data class DrinkCategory(
        @PrimaryKey(autoGenerate = true)
        val id: Int,

        @ColumnInfo(name = "drinksCategory")
        val drinksCategory: String? = null,

        @ColumnInfo(name = "check")
        var check: Boolean = true
): CategoryOrDrink {
    override fun getItemType(): Type = Type.CATEGORY

    fun getDrinkCategoryWithOppositeCheckState() : DrinkCategory{
        return DrinkCategory(
                id = id,
                drinksCategory = drinksCategory,
                check = !check
        )
    }
}