package com.example.somefood.ui.productListClient

import android.os.Bundle
import android.view.*
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.somefood.R
import com.example.somefood.data.model.ProductListModel
import com.example.somefood.data.model.UserModel
import com.example.somefood.databinding.FragmentProductListClientBinding
import com.example.somefood.ui.AddToBuy
import com.example.somefood.ui.OpenDetail
import com.example.somefood.ui.ToFavorite
import com.example.somefood.ui.bottomSheetFragment.CustomBottomSheetDialogFragment
import com.google.android.material.snackbar.Snackbar
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
        val userID = arguments?.getSerializable(USERID) as UserModel
        val menuHost: MenuHost = requireActivity()
        activity?.title = "SomeFood"

        // Меню в туллбаре
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.main_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.myFavorite -> {
                        viewModel.routeToFavorite(userID.id)
                        true
                    }
                    R.id.myBasket -> {
                        viewModel.routeToBascet(userID.id)
                        true
                    }
                    R.id.logOut -> {
                        viewModel.routeToHelloScreen()
                        Snackbar.make(binding.root, "Выход из акканута", Snackbar.LENGTH_SHORT).show()
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)

        myAdapter = ProductListClientAdapter {
            when (it) {
                is OpenDetail -> viewModel.routeToDetail(it.item)
                is ToFavorite -> {
                    viewModel.addNewFavoriteItem(userID.id, it.item.id)
                    Snackbar.make(
                        binding.root,
                        "${it.item.name} добавлен в избранное",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
                is AddToBuy -> {
                    val bottomSheetFragment = CustomBottomSheetDialogFragment()
                    val bundle = bundleOf(
                        Pair("NAME", it.item.name),
                        Pair("USERID", userID.id),
                    )
                    bottomSheetFragment.arguments = bundle
                    bottomSheetFragment.show(parentFragmentManager, bottomSheetFragment.tag)
                }
            }
        }

        with(binding) {
            productRecyclerView.layoutManager = GridLayoutManager(activity, 2)
            productRecyclerView.adapter = myAdapter
        }

        // Установка данных в адаптер
        viewLifecycleOwner.lifecycleScope.launch{
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.list.collect{
                    val array: MutableList<ProductListModel> = mutableListOf()
                    it.forEach {
                        val itemProduct = with(it){ProductListModel(id = id, name = name, description = description, image = image)}
                        array.add(itemProduct)
                    }
                    myAdapter?.set(array)
                }
            }
        }


    }

}