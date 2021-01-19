package com.cocktailDB.room.model

import com.cocktailDB.domain.DomainMapper
import com.cocktailDB.domain.model.DrinkCategory

class DrinkCategoryEntityMapper: DomainMapper<DrinkCategoryEntity, DrinkCategory> {
    override fun mapToDomainModel(model: DrinkCategoryEntity): DrinkCategory {
        return DrinkCategory(
                drinksCategory = model.drinkCategory,
                check = model.check
        )
    }

    override fun mapFromDomainModel(domainModel: DrinkCategory): DrinkCategoryEntity {
        return DrinkCategoryEntity(
                id = 0,
                drinkCategory = domainModel.drinksCategory,
                check = domainModel.check
        )
    }

    fun toDomainList(initial: List<DrinkCategoryEntity>): List<DrinkCategory>{
        return initial.map { mapToDomainModel(it) }
    }

    fun fromDomainList(initial: List<DrinkCategory>): List<DrinkCategoryEntity>{
        return initial.map { mapFromDomainModel(it) }
    }

}