package com.example.somefood.ui.orderList

import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.somefood.R
import com.example.somefood.databinding.FragmentCreatorListBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class CreatorListFragment : Fragment(R.layout.fragment_creator_list) {

    private val viewModel: OrderFragmentViewModel by viewModel()
    private val binding: FragmentCreatorListBinding by viewBinding()


}