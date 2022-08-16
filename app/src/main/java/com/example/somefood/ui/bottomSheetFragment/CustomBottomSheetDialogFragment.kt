package com.example.somefood.ui.bottomSheetFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.somefood.R
import com.example.somefood.databinding.FragmentBottomSheetDialogBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class CustomBottomSheetDialogFragment : BottomSheetDialogFragment() {

    companion object {
        private const val TAG = "AddToOrder"
        private const val KEY = "NAME"
        private const val IMAGE = "IMAGE"
        fun newInstance(name: String, image: String, fragmentManager: FragmentManager) =
            CustomBottomSheetDialogFragment().apply {
                show(fragmentManager, TAG)
                arguments = Bundle().apply {
                    putString(KEY, name)
                    putString(IMAGE, image)
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
        val itemImage = arguments?.getString(IMAGE) ?: ""

        binding.timePicker.setIs24HourView(true)

        binding.buttonAdToBuy.setOnClickListener {
            if (binding.buyPrice.text.isNotEmpty()) {
                val time = "${binding.timePicker.hour}:${binding.timePicker.minute}"
                viewModel.addNewOrder(time, binding.buyPrice.text.toString(), itemName, itemImage)
                dialog?.dismiss()
            } else {
                Toast.makeText(
                    activity,
                    getString(R.string.checkToOrder),
                    Toast.LENGTH_SHORT
                )
                    .show()
            }
        }
    }
}