package com.cocktailDB.ui.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.cocktailDB.BaseApplication
import com.cocktailDB.R
import com.cocktailDB.databinding.MainFragmentBinding
import com.cocktailDB.domain.model.CategoryOrDrink
import com.cocktailDB.ui.main.rv.MainRVAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment : Fragment() {
    @Inject
    lateinit var application: BaseApplication

    private var _binding: MainFragmentBinding? = null

    private val binding get() = _binding!!

    private val TAG = "MainFragment"

    private val viewModel: MainViewModel by viewModels()

    private val position: MutableLiveData<Int> = MutableLiveData(-1)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)

        viewModel.getFirstDrinks()

        val categoriesAndDrinksList = viewModel.categoriesAndDrinks

        viewModel.download.observe(viewLifecycleOwner, {
            if (!it){
                binding.drinkRV.apply {
                    layoutManager = LinearLayoutManager(activity)
                    adapter = MainRVAdapter(categoriesAndDrinksList, position)
                }
            }
        })

        position.observe(viewLifecycleOwner, {
            if (viewModel.categoriesAndDrinks.value != null){
                if (it == viewModel.categoriesAndDrinks.value!!.size - 1){
                    viewModel.changeDrinkCategoriesListIndex()
                }
            }

        })


        viewModel.loading.observe(viewLifecycleOwner, {
            binding.mainFragmentProgressBar.visibility = it
            binding.drinkRV.adapter?.notifyDataSetChanged()
        })


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.mainToolbar.inflateMenu(R.menu.main_fragment_manu)
        binding.mainToolbar.setOnMenuItemClickListener {
            Log.d(TAG, "Click")
            findNavController().navigate(R.id.action_mainFragment_to_filtersFragment)
            viewModel.cleanViewModel()
            true
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}