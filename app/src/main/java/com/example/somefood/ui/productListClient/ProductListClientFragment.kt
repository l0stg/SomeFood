package com.example.somefood.ui.productListClient

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.somefood.R
import com.example.somefood.data.model.FoodDataBaseModel
import com.example.somefood.data.model.ProductListModel
import com.example.somefood.data.model.UserModel
import com.example.somefood.databinding.FragmentProductListClientBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProductListClientFragment : Fragment(R.layout.fragment_product_list_client) {

    private val binding: FragmentProductListClientBinding by viewBinding()
    private val viewModel: ProductListClientViewModel by viewModel()
    private var myAdapter: ProductListClientAdapter? = null

    companion object{
        private const val USERID = "USER_ID"
        fun newInstance(userID: UserModel) = ProductListClientFragment().apply {
            arguments = Bundle().apply {
                putSerializable(USERID, userID)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.title = "SomeFood"
        val userID = arguments?.getSerializable(USERID) as UserModel
        viewModel.addToFood(init())

        myAdapter = ProductListClientAdapter({
            viewModel.routeToDetail(it)
        },{
            /*userID.favorite?.toMutableList()?.add(it.id)
            userID.favorite?.toList()*/
            viewModel.updateFavoriteInUser(userID, it.id)
        })

        with(binding) {
            productRecyclerView.layoutManager = LinearLayoutManager(activity)
            productRecyclerView.adapter = myAdapter
        }

        binding.buttonRouteToFavorite.setOnClickListener {
            viewModel.routeToFavorite(userID.id)
        }

        // Установка данных в адаптер
        viewLifecycleOwner.lifecycleScope.launch{
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.list.collect{
                    val array: MutableList<ProductListModel> = mutableListOf()
                    it.forEach {
                        val itemProduct = ProductListModel(id = it.id!!, name = it.name, description = it.description, image = it.image)
                        array.add(itemProduct)
                    }
                    myAdapter?.set(array)
                }
            }
        }


    }

    private fun init(): MutableList<FoodDataBaseModel>{
        val array: MutableList<FoodDataBaseModel> = mutableListOf()
        var element = FoodDataBaseModel(name = "1", image = R.drawable.img, description = "Вкусный наваристый борщец, ням ням ням")
        array.add(element)
        element = FoodDataBaseModel(name = "2", image = R.drawable.img, description = "Вкусный наваристый борщец, ням ням ням")
        array.add(element)
        element = FoodDataBaseModel(name = "3", image = R.drawable.img, description = "Вкусный наваристый борщец, ням ням ням")
        array.add(element)
        element = FoodDataBaseModel(name = "4", image = R.drawable.img, description = "Вкусный наваристый борщец, ням ням ням")
        array.add(element)
        element = FoodDataBaseModel(name = "5", image = R.drawable.img, description = "Вкусный наваристый борщец, ням ням ням")
        array.add(element)
        return array
    }
}