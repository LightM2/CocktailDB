package com.cocktailDB.ui.filters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.cocktailDB.BaseApplication
import com.cocktailDB.databinding.FiltersFragmentBinding
import com.cocktailDB.domain.model.DrinkCategory
import com.cocktailDB.ui.filters.rv.FiltersRVAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FiltersFragment: Fragment() {
    @Inject
    lateinit var application: BaseApplication

    private var _binding: FiltersFragmentBinding? = null

    private val binding get() = _binding!!

    private val TAG = "MainFragment"

    private val viewModel: FiltersViewModel by viewModels()

    private lateinit var drinkCategories: LiveData<List<DrinkCategory>>

    private val updateDrinkCategory: MutableLiveData<DrinkCategory> = MutableLiveData()

    private val updateDrinkCategories: MutableList<DrinkCategory> = mutableListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View{
        _binding = FiltersFragmentBinding.inflate(inflater, container, false)

        drinkCategories = viewModel.drinkCategories

        updateDrinkCategory.observe(viewLifecycleOwner, {
            when {
                updateDrinkCategories.isEmpty() -> updateDrinkCategories.add(it)
                updateDrinkCategories.contains(it.getDrinkCategoryWithOppositeCheckState()) -> {
                    val index = updateDrinkCategories.indexOf(it.getDrinkCategoryWithOppositeCheckState())
                    updateDrinkCategories.add(index, it)
                }
                else -> updateDrinkCategories.add(it)
            }
        })

        viewModel.download.observe(viewLifecycleOwner, {
            if (!it){
                binding.filtersRV.apply {
                    layoutManager = LinearLayoutManager(activity)
                    adapter = FiltersRVAdapter(drinkCategories, updateDrinkCategory)
                }
            }
        })


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.applyButton.setOnClickListener {
            viewModel.updateDrinkCategoryList(updateDrinkCategories)
            findNavController().popBackStack()
        }

        binding.filterFragmentToolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}