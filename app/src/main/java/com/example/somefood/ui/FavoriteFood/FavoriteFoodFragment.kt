package com.example.somefood.ui.FavoriteFood

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.os.bundleOf
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
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

    private var myAdapter: FavoriteAdapter? = null
    private val binding: FragmentFavoriteFoodBinding by viewBinding()
    private val viewModel: FavoriteViewModel by viewModel()

    companion object {
        private const val USERID = "USER_ID"
        fun newInstance(userID: Int?) = FavoriteFoodFragment().apply {
            arguments = Bundle().apply {
                if (userID != null) {
                    putInt(USERID, userID)
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.title = "Избранное"

        val userID = arguments?.getInt(USERID) as Int

        viewModel.updateUI(userID)

        updateDataInUI()

        myAdapter = FavoriteAdapter {
            when(it){
                is OpenDetail -> viewModel.routeToFavorite(it.item)
                is ToFavorite -> viewModel.deleteFood(it.item.id, userID)
                is AddToBuy -> {
                    val bottomSheetFragment = CustomBottomSheetDialogFragment()
                    val bundle = bundleOf(
                        Pair("NAME", it.item.name),
                        Pair("USERID", userID),
                    )
                    bottomSheetFragment.arguments = bundle
                    bottomSheetFragment.show(parentFragmentManager, bottomSheetFragment.tag)
                }
            }
        }

        with(binding) {
            favoriteFoodRecyclerView.layoutManager = GridLayoutManager(activity, 2)
            favoriteFoodRecyclerView.adapter = myAdapter
        }
    }

    private fun updateDataInUI() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.list.collect {
                    val array: MutableList<ProductListModel> = mutableListOf()
                    it.forEach {
                        val itemProduct = ProductListModel(id = it.id, name = it.name, description = it.description, image = it.image)
                        array.add(itemProduct)
                    }
                    myAdapter?.set(array)
                }
            }
        }
    }
}
