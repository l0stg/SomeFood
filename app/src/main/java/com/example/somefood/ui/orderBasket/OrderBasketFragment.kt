package com.example.somefood.ui.orderBasket

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Index
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.somefood.R
import com.example.somefood.data.model.ProductListModel
import com.example.somefood.data.model.UserModel
import com.example.somefood.databinding.FragmentOrderBasketBinding
import com.example.somefood.ui.productListClient.ProductListClientFragment
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class OrderBasketFragment : Fragment(R.layout.fragment_order_basket) {

    private val binding: FragmentOrderBasketBinding by viewBinding()
    private val viewModel: OrderBasketViewModel by viewModel()
    private var myAdapter: OrderBasketAdapter? = null


    companion object{
        private const val USERID = "USER_ID"
        fun newInstance(userID: Int) = OrderBasketFragment().apply {
            arguments = Bundle().apply {
                putInt(USERID, userID)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userID = arguments?.getInt(USERID)

        if (userID != null) {
            viewModel.checkOrderByClient(userID)
        }

        myAdapter = OrderBasketAdapter()

        with(binding){
            orderBasketRecyclerView.layoutManager = LinearLayoutManager(activity)
            orderBasketRecyclerView.adapter = myAdapter
        }

        viewLifecycleOwner.lifecycleScope.launch{
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.list.collect{
                    myAdapter?.set(it)
                }
            }
        }
    }
}