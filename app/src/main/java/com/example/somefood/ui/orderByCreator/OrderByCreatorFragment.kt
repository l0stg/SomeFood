package com.example.somefood.ui.orderByCreator

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.somefood.R
import com.example.somefood.data.model.Status
import com.example.somefood.databinding.FragmentOrderByCreatorBinding
import com.example.somefood.ui.ItemInOrderClick
import com.example.somefood.ui.OpenDetailInfo
import com.example.somefood.ui.bottomSheetRating.BottomSheetRatingFragment
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class OrderByCreatorFragment : Fragment(R.layout.fragment_order_by_creator) {

    private val binding: FragmentOrderByCreatorBinding by viewBinding()
    private val viewModel: OrderByCreatorViewModel by viewModel()
    private var myAdapter: OrderByCreatorAdapter? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.title = getString(R.string.myOrderByCreator)

        updateDataInUI()

        myAdapter = OrderByCreatorAdapter {
            when(it) {
                is ItemInOrderClick -> {
                    viewModel.addInJob(it.item)
                    if (it.item.status == Status.JOB) {
                        BottomSheetRatingFragment.show(
                            it.item.userID,
                            it.item.id,
                            childFragmentManager
                        )
                    }
                }
                is OpenDetailInfo -> {
                    viewModel.routeToDetailInfo(it.item.foodId)
                }
            }
        }
        with(binding) {
            orderByCreatorRecyclerView.layoutManager = LinearLayoutManager(activity)
            orderByCreatorRecyclerView.adapter = myAdapter
        }
    }

    private fun updateDataInUI() {
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