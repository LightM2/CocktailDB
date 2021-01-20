package com.cocktailDB.ui.main

import android.util.Log
import android.view.View
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.cocktailDB.domain.model.CategoryOrDrink
import com.cocktailDB.domain.model.Drink
import com.cocktailDB.domain.model.DrinkCategory
import com.cocktailDB.repository.DrinkRepository
import kotlinx.coroutines.launch

class MainViewModel
@ViewModelInject
constructor(
        private val repository: DrinkRepository,
        @Assisted private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val TAG = "MainViewModel"

    private val _drinkCategories: MutableLiveData<List<DrinkCategory>> = MutableLiveData()
    val drinkCategories: LiveData<List<DrinkCategory>>
        get() = _drinkCategories

    private val drinks: MutableLiveData<List<Drink>> = MutableLiveData()

    private val _categoriesAndDrinks: MutableLiveData<List<CategoryOrDrink>> = MutableLiveData()
    val categoriesAndDrinks: LiveData<List<CategoryOrDrink>>
        get() = _categoriesAndDrinks

    private val drinkCategoriesListIndex: MutableLiveData<Int> = MutableLiveData(0)

    private val _download: MutableLiveData<Boolean> = MutableLiveData(true)
    val download: LiveData<Boolean>
        get() = _download

    private val _loading: MutableLiveData<Int> = MutableLiveData(View.INVISIBLE)
    val loading: LiveData<Int>
        get() = _loading


    private suspend fun getDrinkCategories() {
        val result = repository.getDrinkCategoriesFromDB()
        if (!result.isNullOrEmpty()) {
            _drinkCategories.value = result
            Log.d(TAG, "getDrinkCategoriesFromDB")
            Log.d(TAG, "getDrinkCategoriesFromDB - $result")
        } else getDrinkCategoriesFromNetwork()

    }

    fun cleanViewModel(){
        _categoriesAndDrinks.value = listOf()
        drinkCategoriesListIndex.value = 0
    }


    private suspend fun getDrinkCategoriesFromNetwork() {
        val result = repository.getDrinkCategoriesFromNetwork("list")
        _drinkCategories.value = result
        repository.setDrinkCategoriesToDB(result)
        Log.d(TAG, "getDrinkCategoriesFromNetwork")
    }

    private suspend fun getDrinksFromCategory(category: String) {
        val result = repository.getDrinksFromNetwork(category)
        this.drinks.value = result
    }

    fun getFirstDrinks() {
        viewModelScope.launch {
            val list: MutableList<CategoryOrDrink> = mutableListOf()
            getDrinkCategories()
            getDrinks(list)


        }
    }

    private fun getNextDrinks() {
        viewModelScope.launch {
            val list: MutableList<CategoryOrDrink> = mutableListOf()
            if (categoriesAndDrinks.value != null) {
                list.addAll(categoriesAndDrinks.value!!)
            }
            getDrinks(list)
        }

    }

    private suspend fun getDrinks(list: MutableList<CategoryOrDrink>) {
        if (drinkCategories.value!![drinkCategoriesListIndex.value!!].check) {
            list.add(drinkCategories.value!![drinkCategoriesListIndex.value!!])
            getDrinksFromCategory(drinkCategories.value!![drinkCategoriesListIndex.value!!].drinksCategory!!)
            list.addAll(drinks.value!!)
            _categoriesAndDrinks.value = list
            _loading.value = View.INVISIBLE
            if (download.value == true) {
                _download.value = false
            }
        } else changeDrinkCategoriesListIndex()
    }

    //Пагінація
    fun changeDrinkCategoriesListIndex() {
        Log.d(TAG, "changeDrinkCategoriesListIndex")
        if (drinkCategoriesListIndex.value!! < drinkCategories.value!!.size - 1) {
            drinkCategoriesListIndex.value = drinkCategoriesListIndex.value!! + 1
            Log.d(TAG, "changeDrinkCategoriesListIndex WORK")
            getNextDrinks()
        }

    }


}