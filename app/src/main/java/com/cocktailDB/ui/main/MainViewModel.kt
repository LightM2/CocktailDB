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
)  : ViewModel() {

    private val TAG = "MainViewModel"

    private val _drinkCategories: MutableLiveData<List<DrinkCategory>> = MutableLiveData()
    private val drinkCategories: LiveData<List<DrinkCategory>>
        get() = _drinkCategories

    private val _drinks: MutableLiveData<List<Drink>> = MutableLiveData()
    private val drinks: LiveData<List<Drink>>
        get() = _drinks

    private val _categoriesAndDrinks: MutableLiveData<List<CategoryOrDrink>> = MutableLiveData()
    val categoriesAndDrinks: LiveData<List<CategoryOrDrink>>
        get() = _categoriesAndDrinks

    private val _drinkCategoriesListIndex: MutableLiveData<Int> = MutableLiveData(0)
    private val drinkCategoriesListIndex: LiveData<Int>
        get() = _drinkCategoriesListIndex

    private val _download: MutableLiveData<Boolean> = MutableLiveData(true)
    val download: LiveData<Boolean>
        get() = _download

    private val _loading: MutableLiveData<Int> = MutableLiveData(View.INVISIBLE)
    val loading: LiveData<Int>
        get() = _loading

    init {
        getDrinks()
    }

    private suspend fun getDrinkCategories(){
        val result = repository.getDrinkCategoriesFromDB()
        if (result != null){
            _drinkCategories.value = result
            Log.d(TAG, "getDrinkCategoriesFromDB")
        }else getDrinkCategoriesFromNetwork()

    }

    private suspend fun getDrinkCategoriesFromNetwork(){
        val result = repository.getDrinkCategoriesFromNetwork("list")
        _drinkCategories.value = result
        repository.setDrinkCategoriesToDB(result)
        Log.d(TAG, "getDrinkCategoriesFromNetwork")
    }

    private suspend fun getDrinksFromCategory(category: String){
        val result = repository.getDrinksFromNetwork(category)
        _drinks.value = result
        Log.d(TAG, "drinks 0 - ${drinks.value?.get(0)}")
    }

    private fun getDrinks(){
        viewModelScope.launch {
            val list: MutableList<CategoryOrDrink> = mutableListOf()
            if (categoriesAndDrinks.value == null){
                getDrinkCategories()
            }else {
                list.addAll(categoriesAndDrinks.value!!)
            }
            if (drinkCategories.value!![drinkCategoriesListIndex.value!!].check){
                list.add(drinkCategories.value!![drinkCategoriesListIndex.value!!])
                getDrinksFromCategory(drinkCategories.value!![drinkCategoriesListIndex.value!!].drinksCategory!!)
                list.addAll(drinks.value!!)
                _categoriesAndDrinks.value = list
                _loading.value = View.INVISIBLE
                if (download.value == true){
                    _download.value = false
                }
            }else changeDrinkCategoriesListIndex()


        }
    }

    fun changeDrinkCategoriesListIndex(){
        Log.d(TAG, "changeDrinkCategoriesListIndex")
        if (drinkCategoriesListIndex.value!! < drinkCategories.value!!.size - 1){
            _drinkCategoriesListIndex.value = _drinkCategoriesListIndex.value!! + 1
            Log.d(TAG, "changeDrinkCategoriesListIndex WORK")
            getDrinks()
        }

    }


}