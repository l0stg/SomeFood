package com.example.somefood.ui.productListClient

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.somefood.R
import com.example.somefood.data.model.ProductListModel
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

    companion object {
        fun newInstance() = ProductListClientFragment()
        private const val GRIDCONST = 2
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val menuHost: MenuHost = requireActivity()
        activity?.title = getString(R.string.menu)

        // Меню в туллбаре
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.main_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.myFavorite -> {
                        viewModel.routeToFavorite()
                        true
                    }
                    R.id.myBasket -> {
                        viewModel.routeToBascet()
                        true
                    }
                    R.id.logOut -> {
                        viewModel.routeToHelloScreen()
                        Snackbar.make(
                            binding.root,
                            getString(R.string.signOut),
                            Snackbar.LENGTH_SHORT
                        ).show()
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
                    viewModel.addNewFavoriteItem(it.item.id)
                    Snackbar.make(
                        binding.root,
                        getString(R.string.addToFavorite),
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
                is AddToBuy -> {
                    CustomBottomSheetDialogFragment.newInstance(
                        it.item.name,
                        it.item.image,
                        childFragmentManager
                    )
                }
            }
        }

        with(binding) {
            productRecyclerView.layoutManager = GridLayoutManager(activity, GRIDCONST)
            productRecyclerView.adapter = myAdapter
        }

        // Установка данных в адаптер
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.list.collect {
                    myAdapter?.set(it.map {
                        ProductListModel(
                            id = it.id,
                            name = it.name,
                            description = it.recept,
                            image = it.image
                        )
                    })
                }
            }
        }
    }
}