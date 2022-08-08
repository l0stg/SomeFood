package com.example.somefood.ui.bottomSheetFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.somefood.R
import com.example.somefood.data.model.ProductListModel
import com.example.somefood.databinding.FragmentBottomSheetDialogBinding
import com.example.somefood.ui.FavoriteFood.FavoriteFoodFragment
import com.example.somefood.ui.detailFood.DetailFoodFragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class CustomBottomSheetDialogFragment : BottomSheetDialogFragment() {

    companion object{
        const val TAG = "AddToOrder"
        const val KEY = "NAME"
        fun newInstance(name: String) = CustomBottomSheetDialogFragment().apply {
            arguments = Bundle().apply {
                putString(KEY, name)
            }
        }
    }

    private val binding: FragmentBottomSheetDialogBinding by viewBinding()

    private val viewModel: DialogViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_bottom_sheet_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val itemName = arguments?.getString(KEY) ?: ""

        binding.timePicker.setIs24HourView(true)

        var time = "01:00"
        binding.timePicker.setOnTimeChangedListener { _, hour, minutes ->
            time = "$hour : $minutes"
        }

        binding.buttonAdToBuy.setOnClickListener {
            if(binding.buyPrice.text.isNotEmpty() ){
                viewModel.addNewOrder(time, binding.buyPrice.text.toString(), itemName)
                dialog?.dismiss()
            }else Toast.makeText(activity, "Уточните заказ", Toast.LENGTH_SHORT).show()
        }
    }


}