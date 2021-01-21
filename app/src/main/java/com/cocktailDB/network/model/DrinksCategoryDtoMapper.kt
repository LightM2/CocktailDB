package com.cocktailDB.network.model

import androidx.room.PrimaryKey
import com.cocktailDB.domain.DomainMapper
import com.cocktailDB.domain.model.DrinkCategory

class DrinksCategoryDtoMapper : DomainMapper<DrinksCategoryDto, DrinkCategory> {
    override fun mapToDomainModel(model: DrinksCategoryDto): DrinkCategory {
        return DrinkCategory(
                id = 0,
                drinksCategory = model.strCategory,
                check = true
        )
    }

    override fun mapFromDomainModel(domainModel: DrinkCategory): DrinksCategoryDto {
        return DrinksCategoryDto(
                strCategory = domainModel.drinksCategory
        )
    }

    fun toDomainList(initial: List<DrinksCategoryDto>?): List<DrinkCategory>? {
        return initial?.map { mapToDomainModel(it) }
    }


}