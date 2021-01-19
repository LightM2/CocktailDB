package com.cocktailDB.ui.filters

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.cocktailDB.repository.DrinkRepository

class FiltersViewModel @ViewModelInject
constructor(
        private val repository: DrinkRepository,
        @Assisted private val savedStateHandle: SavedStateHandle,
)  : ViewModel()