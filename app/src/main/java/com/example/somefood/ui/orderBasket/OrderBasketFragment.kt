package com.example.somefood.ui.orderBasket

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.somefood.R
import com.example.somefood.databinding.FragmentOrderBasketBinding
import com.example.somefood.ui.ItemInOrderClick
import com.example.somefood.ui.OpenDetailInfo
import com.example.somefood.ui.bottomSheetRating.BottomSheetRatingFragment
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class OrderBasketFragment : Fragment(R.layout.fragment_order_basket) {

    private val binding: FragmentOrderBasketBinding by viewBinding()
    private val viewModel: OrderBasketViewModel by viewModel()
    private var myAdapter: OrderBasketAdapter? = null


    companion object {
        fun newInstance() = OrderBasketFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.title = getString(R.string.myBascet)
        viewModel.checkOrderByClient()

        myAdapter = OrderBasketAdapter {
            when(it){
                is ItemInOrderClick -> {
                    viewModel.pickUpOrder(it.item)
                    Snackbar.make(
                        binding.root,
                        getString(R.string.goodEat),
                        Snackbar.LENGTH_SHORT
                    ).show()
                    BottomSheetRatingFragment.show(it.item.userIdGoToJob, it.item.id, childFragmentManager)}
                is OpenDetailInfo -> {
                    viewModel.routeToDetailInfo(it.item.foodId)
                }
            }
        }

        with(binding) {
            orderBasketRecyclerView.layoutManager = LinearLayoutManager(activity)
            orderBasketRecyclerView.adapter = myAdapter
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.list.collect {
                    myAdapter?.set(it)
                    if (it.isNotEmpty()) {
                        binding.emptyView.visibility = View.GONE
                    } else {
                        binding.emptyView.visibility = View.VISIBLE
                    }
                }
            }
        }
    }
}