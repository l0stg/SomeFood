package com.example.somefood.ui.FavoriteFood

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.somefood.R
import com.example.somefood.data.model.ProductListModel
import com.example.somefood.databinding.FragmentFavoriteFoodBinding
import com.example.somefood.ui.AddToBuy
import com.example.somefood.ui.OpenDetail
import com.example.somefood.ui.ToFavorite
import com.example.somefood.ui.bottomSheetFragment.CustomBottomSheetDialogFragment
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class FavoriteFoodFragment : Fragment(R.layout.fragment_favorite_food) {

    private val binding: FragmentFavoriteFoodBinding by viewBinding()
    private var myAdapter: FavoriteAdapter? = null

    private val viewModel: FavoriteViewModel by viewModel()

    companion object {
        fun newInstance() = FavoriteFoodFragment()
        private const val GRIDCONST = 2
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.title = getString(R.string.Favorite)

        viewModel.updateUI()

        updateDataInUI()

        myAdapter = FavoriteAdapter {
            when (it) {
                is OpenDetail -> viewModel.routeToFavorite(it.item)
                is ToFavorite -> viewModel.deleteFood(it.item.id)
                is AddToBuy -> {
                    CustomBottomSheetDialogFragment.show(
                        it.item.name,
                        it.item.image,
                        it.item.id,
                        childFragmentManager
                    )
                }
            }
        }

        with(binding) {
            favoriteFoodRecyclerView.layoutManager = GridLayoutManager(activity, GRIDCONST)
            favoriteFoodRecyclerView.adapter = myAdapter
        }
    }

    private fun updateDataInUI() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.list.collect {
                    myAdapter?.set(it.map {
                        ProductListModel(
                            id = it.id,
                            name = it.name,
                            recept = it.recept,
                            image = it.image,
                        )
                    })
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

