package com.cocktailDB.ui.filters

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.cocktailDB.domain.model.DrinkCategory
import com.cocktailDB.repository.DrinkRepository
import kotlinx.coroutines.launch

class FiltersViewModel @ViewModelInject
constructor(
        private val repository: DrinkRepository,
        @Assisted private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val TAG = "FiltersViewModel"

    private val _drinkCategories: MutableLiveData<List<DrinkCategory>> = MutableLiveData()
    val drinkCategories: LiveData<List<DrinkCategory>>
        get() = _drinkCategories

    private val _download: MutableLiveData<Boolean> = MutableLiveData(true)
    val download: LiveData<Boolean>
        get() = _download

    init {
        getListOfDrinkCategoriesFromDB()
    }

    private fun getListOfDrinkCategoriesFromDB(){
        viewModelScope.launch {
            val result = repository.getDrinkCategoriesFromDB()
            _drinkCategories.value = result
            if (download.value == true){
                _download.value = false
            }

        }

    }

    fun updateDrinkCategoryList(updateData: List<DrinkCategory>){
        viewModelScope.launch {
            repository.updateDrinkCategoriesInDB(updateData)

        }
    }


}