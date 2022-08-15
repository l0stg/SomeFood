package com.example.somefood.ui.orderByCreator

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.somefood.R
import com.example.somefood.databinding.FragmentOrderByCreatorBinding
import com.example.somefood.ui.orderList.OrderAdapter
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class OrderByCreatorFragment() : Fragment(R.layout.fragment_order_by_creator) {

    private val binding: FragmentOrderByCreatorBinding by viewBinding()
    private val viewModel: OrderByCreatorViewModel by viewModel()
    private var myAdapter: OrderByCreatorAdapter? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        updateDataInUI()

        myAdapter = OrderByCreatorAdapter{
            viewModel.addInJob(it)
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
                }
            }
        }
    }

}