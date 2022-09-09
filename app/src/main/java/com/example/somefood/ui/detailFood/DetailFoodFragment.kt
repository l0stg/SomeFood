package com.example.somefood.ui.detailFood

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.somefood.R
import com.example.somefood.databinding.FragmentDetailFoodBinding
import com.example.somefood.ui.bottomSheetFragment.NewOrderBottomSheetFragment
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFoodFragment : Fragment(R.layout.fragment_detail_food) {

    private val binding: FragmentDetailFoodBinding by viewBinding()
    private val viewModel: DetailFoodViewModel by viewModel()

    companion object {
        private const val ARG_FOODID = "FOODID"
        fun newInstance(foodId: Int) = DetailFoodFragment().apply {
            arguments = Bundle().apply {
                putInt(ARG_FOODID, foodId)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val foodId = arguments?.getInt(ARG_FOODID) ?: 0

        viewModel.getInfo(foodId)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.model.filterNotNull().collect { model ->
                    activity?.title = model.name
                    with(binding) {
                        tvNameDetail.text = model.name
                        tvDescriptionDetail.text = model.recept
                        Glide
                            .with(ivDetail.context)
                            .load(model.image)
                            .centerCrop()
                            .placeholder(R.drawable.ic_launcher_foreground)
                            .into(ivDetail)
                        if (model.idFavorite != null)
                            addToFavorite.setBackgroundColor(Color.RED)
                        else
                            addToFavorite.setBackgroundColor(Color.GREEN)
                        addToBuy.setOnClickListener {
                            NewOrderBottomSheetFragment.show(
                                model.name,
                                model.image,
                                model.id,
                                childFragmentManager
                            )
                        }
                        addToFavorite.setOnClickListener {
                            viewModel.addNewFavoriteItem(model.id)
                            Snackbar.make(
                                binding.root,
                                getString(R.string.addToFavorite),
                                Snackbar.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }
        }
    }
}