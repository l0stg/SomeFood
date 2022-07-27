package com.example.somefood.ui.productListClient

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.somefood.R
import com.example.somefood.databinding.FragmentProductListClientBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProductListClientFragment : Fragment(R.layout.fragment_product_list_client) {

    private val binding: FragmentProductListClientBinding by viewBinding()
    private val viewModel: ProductListClientViewModel by viewModel()
    private var myAdapter: ProductListClientAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.title = "SomeFood"

        myAdapter = ProductListClientAdapter{
            viewModel.routeToDetail()
        }
        with(binding) {
            productRecyclerView.layoutManager = LinearLayoutManager(activity)
            productRecyclerView.adapter = myAdapter
        }

        val array: List<Int> = listOf(1,2,3,4,5,6,7,8,90,1,233,234,3,3,3,3,3,3,3,3)
        myAdapter?.set(array)
        binding.buttonRouteToFavorite.setOnClickListener {
            viewModel.routeToFavorite()
        }


    }
}