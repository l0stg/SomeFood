package com.example.somefood.ui.FavoriteFood

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.somefood.R
import com.example.somefood.data.model.ProductListModel
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
        val array: MutableList<ProductListModel> = mutableListOf()
        val element = ProductListModel("Борщ", R.drawable.img, "Добавленный в избранное", true )
        val element2 = ProductListModel("Борщ", R.drawable.img, "Добавленный в избранное", false )
        repeat(2){
            array.add(element)
            array.add(element2)
        }
        val array1: MutableList<ProductListModel> = mutableListOf()
        array.forEach{
            if (it.favorite){
                array1.add(it)
            }
        }
        myAdapter?.set(array1)
    }



}