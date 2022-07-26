package com.example.somefood.ui.FavoriteFood

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.somefood.R
import com.example.somefood.databinding.FragmentFavoriteFoodBinding
import com.example.somefood.ui.productListClient.ProductListClientAdapter


class FavoriteFoodFragment : Fragment(R.layout.fragment_favorite_food) {

    private var myAdapter: ProductListClientAdapter? = null
    private val binding: FragmentFavoriteFoodBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.title = "Избаранное"

        myAdapter = ProductListClientAdapter{}
        with(binding) {
            favoriteFoodRecyclerView.layoutManager = LinearLayoutManager(activity)
            favoriteFoodRecyclerView.adapter = myAdapter
        }
        val array: List<Int> = listOf(1,2)
        myAdapter?.set(array)
    }



}