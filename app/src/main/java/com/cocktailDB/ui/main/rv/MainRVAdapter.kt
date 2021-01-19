package com.cocktailDB.ui.main.rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.cocktailDB.R
import com.cocktailDB.databinding.MainFragmentRvElementBinding
import com.cocktailDB.databinding.MainFragmentRvHeaderBinding
import com.cocktailDB.domain.model.CategoryOrDrink
import com.cocktailDB.domain.model.Drink
import com.cocktailDB.domain.model.DrinkCategory
import com.cocktailDB.domain.model.Type
import com.cocktailDB.ui.main.MainViewModel
import com.squareup.picasso.Picasso

class MainRVAdapter(private val categoriesAndDrinks: LiveData<List<CategoryOrDrink>>, private val rvPosition: MutableLiveData<Int>)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return if (viewType == 1){
            val binding: MainFragmentRvHeaderBinding = DataBindingUtil.
            inflate(layoutInflater, R.layout.main_fragment_rv_header, parent, false)
            MainRVHeaderHolder(binding)
        }else{
            val binding: MainFragmentRvElementBinding = DataBindingUtil.
            inflate(layoutInflater, R.layout.main_fragment_rv_element, parent, false)
            MainRVElementHolder(binding)
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        rvPosition.value = position
        val categoryOrDrink = categoriesAndDrinks.value?.get(position)
        when {
            holder is MainRVHeaderHolder && categoryOrDrink is DrinkCategory -> {
                holder.binding.dinkCategory = categoryOrDrink
            }
            holder is MainRVElementHolder && categoryOrDrink is Drink -> {
                Picasso.get()
                        .load(categoryOrDrink.drinkPhoto)
                        .centerCrop(100)
                        .fit()
                        .error(R.drawable.ic_baseline_image_not_supported)
                        .into(holder.binding.drinkIV)
                holder.binding.dink = categoryOrDrink
            }
        }


    }

    override fun getItemCount(): Int = categoriesAndDrinks.value!!.size

    override fun getItemViewType(position: Int): Int {
        return when(categoriesAndDrinks.value!![position].getItemType()){
            Type.DRINK -> 0
            Type.CATEGORY -> 1
        }
    }

}

