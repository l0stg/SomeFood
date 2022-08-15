package com.example.somefood.ui.orderList

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
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.somefood.R
import com.example.somefood.databinding.FragmentCreatorListBinding
import com.example.somefood.ui.FavoriteFood.FavoriteAdapter
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class CreatorListFragment : Fragment(R.layout.fragment_creator_list) {

    private val viewModel: OrderFragmentViewModel by viewModel()
    private val binding: FragmentCreatorListBinding by viewBinding()
    private var myAdapter: OrderAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val menuHost: MenuHost = requireActivity()

        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.order_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.logOut -> {
                        viewModel.routeToHelloScreen()
                        Snackbar.make(binding.root, getString(R.string.signOut), Snackbar.LENGTH_SHORT).show()
                        true
                    }
                    R.id.goToOrder -> {
                        viewModel.routeToOrderByCreator()
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)

        activity?.title = getString(R.string.order)

        updateDataInUI()

        myAdapter = OrderAdapter{
            viewModel.addInJob(it)
        }
        with(binding) {
            orderRecyclerView.layoutManager = LinearLayoutManager(activity)
            orderRecyclerView.adapter = myAdapter
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