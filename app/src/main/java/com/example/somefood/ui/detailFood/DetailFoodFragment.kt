package com.example.somefood.ui.detailFood

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.somefood.R
import com.example.somefood.data.model.ProductListModel
import com.example.somefood.databinding.FragmentDetailFoodBinding
import com.example.somefood.ui.bottomSheetFragment.CustomBottomSheetDialogFragment
import com.example.somefood.ui.productListClient.ProductListClientFragment
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFoodFragment : Fragment(R.layout.fragment_detail_food) {

    private val binding: FragmentDetailFoodBinding by viewBinding()
    private val viewModel: DetailFoodViewModel by viewModel()

    companion object{
        private const val MODEL = "MODEL"
        fun newInstance(model: ProductListModel) = DetailFoodFragment().apply {
            arguments = Bundle().apply {
                putParcelable(MODEL, model)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       val model = arguments?.getParcelable<ProductListModel>(MODEL) as ProductListModel
        activity?.title = model.name
        with(binding){
            tvNameDetail.text = model.name
            tvDescriptionDetail.text = model.description
            Glide
                .with(ivDetail.context)
                .load(model.image)
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(ivDetail)
            addToBuy.setOnClickListener {
                CustomBottomSheetDialogFragment.newInstance(model.name, model.image, childFragmentManager)
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