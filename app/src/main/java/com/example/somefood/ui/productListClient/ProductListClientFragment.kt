package com.example.somefood.ui.productListClient

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.somefood.R
import com.example.somefood.data.model.ProductListModel
import com.example.somefood.databinding.FragmentProductListClientBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProductListClientFragment : Fragment(R.layout.fragment_product_list_client) {

    private val binding: FragmentProductListClientBinding by viewBinding()
    private val viewModel: ProductListClientViewModel by viewModel()
    private var myAdapter: ProductListClientAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.title = "SomeFood"

        myAdapter = ProductListClientAdapter({
            viewModel.routeToDetail()
        },{
            viewModel.addToFavorite(it)
        })
        with(binding) {
            productRecyclerView.layoutManager = LinearLayoutManager(activity)
            productRecyclerView.adapter = myAdapter
        }

        val array: MutableList<ProductListModel> = mutableListOf()
        var element = ProductListModel("1", R.drawable.img, "Вкусный наваристый борщец, ням ням ням")
        array.add(element)
        element = ProductListModel("2", R.drawable.img, "Вкусный наваристый борщец, ням ням ням")
        array.add(element)
        element = ProductListModel("3", R.drawable.img, "Вкусный наваристый борщец, ням ням ням")
        array.add(element)
        element = ProductListModel("4", R.drawable.img, "Вкусный наваристый борщец, ням ням ням")
        array.add(element)
        element = ProductListModel("5", R.drawable.img, "Вкусный наваристый борщец, ням ням ням")
        array.add(element)
        myAdapter?.set(array.toList())
        binding.buttonRouteToFavorite.setOnClickListener {
            viewModel.routeToFavorite()
        }


    }
}