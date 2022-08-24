package com.example.somefood.ui.historyOrderFragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.somefood.R
import com.example.somefood.databinding.FragmentHistoryOrderBinding
import com.example.somefood.ui.FavoriteFood.FavoriteFoodFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class HistoryOrderFragment : Fragment(R.layout.fragment_history_order) {

    companion object {
        fun newInstance() = HistoryOrderFragment()
    }

    private val viewModel: HistoryOrderViewModel by viewModel()
    private val binding: FragmentHistoryOrderBinding by viewBinding()
    private var myAdapter: HistoryOrderAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        myAdapter = HistoryOrderAdapter()

        with(binding) {
            historyOrderRecyclerView.layoutManager = LinearLayoutManager(activity)
            historyOrderRecyclerView.adapter = myAdapter
        }



    }
}