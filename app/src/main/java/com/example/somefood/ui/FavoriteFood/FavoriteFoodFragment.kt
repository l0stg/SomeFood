package com.example.somefood.ui.FavoriteFood

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.somefood.R
import com.example.somefood.data.model.ProductListModel
import com.example.somefood.databinding.FragmentFavoriteFoodBinding
import com.example.somefood.ui.productListClient.ProductListClientAdapter
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class FavoriteFoodFragment : Fragment(R.layout.fragment_favorite_food) {

    private var myAdapter: FavoriteAdapter? = null
    private val binding: FragmentFavoriteFoodBinding by viewBinding()
    private val viewModel: FavoriteViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.title = "Избаранное"

        updateDataInUI()
        myAdapter = FavoriteAdapter{
            viewModel.deleteFood(it)
        }
        with(binding) {
            favoriteFoodRecyclerView.layoutManager = LinearLayoutManager(activity)
            favoriteFoodRecyclerView.adapter = myAdapter
        }
    }
    private fun updateDataInUI() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.list.collect {
                    myAdapter?.set(it)
                }
            }
        }
    }



}