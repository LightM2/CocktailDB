package com.cocktailDB.ui.filters.rv

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.cocktailDB.R
import com.cocktailDB.databinding.FiltersFragmentRvElementBinding
import com.cocktailDB.domain.model.DrinkCategory

class FiltersRVAdapter (private val drinkCategoriesList: LiveData<List<DrinkCategory>>,
                        private val updateDrinkCategory: MutableLiveData<DrinkCategory>)
    : RecyclerView.Adapter<FiltersRVElementHolder>() {
    private val TAG = "FiltersRVAdapter"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FiltersRVElementHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding : FiltersFragmentRvElementBinding = DataBindingUtil.
        inflate(layoutInflater, R.layout.filters_fragment_rv_element, parent, false)
        return FiltersRVElementHolder(binding)
    }

    override fun onBindViewHolder(holder: FiltersRVElementHolder, position: Int) {
        val data = drinkCategoriesList.value!![position]
        holder.binding.dinkCategory = data
        //holder.binding.rvElementCheckBox.isChecked = data.check
        holder.binding.rvElementCheckBox.setOnClickListener {

        }
        holder.binding.rvElementCheckBox.setOnCheckedChangeListener { compoundButton, state ->
            if (data.check != state){
                Log.d(TAG, "OnClick drinksCategory - ${data.drinksCategory}")
                data.check = state
                updateDrinkCategory.value = data
            }

        }

    }

    override fun getItemCount(): Int = drinkCategoriesList.value!!.size
}