package com.cocktailDB.network.model

import com.cocktailDB.domain.DomainMapper
import com.cocktailDB.domain.model.Drink

class DrinkDtoMapper: DomainMapper<DrinkDto, Drink> {
    override fun mapToDomainModel(model: DrinkDto): Drink {
        return Drink(
            id = model.idDrink,
            drinkName = model.strDrink,
            drinkPhoto = model.strDrinkThumb
        )
    }

    override fun mapFromDomainModel(domainModel: Drink): DrinkDto {
        return DrinkDto(
                idDrink = domainModel.id,
                strDrink = domainModel.drinkName,
                strDrinkThumb = domainModel.drinkPhoto
        )
    }



    fun toDomainList(initial: List<DrinkDto>?): List<Drink>?{
        return initial?.map { mapToDomainModel(it) }
    }



}