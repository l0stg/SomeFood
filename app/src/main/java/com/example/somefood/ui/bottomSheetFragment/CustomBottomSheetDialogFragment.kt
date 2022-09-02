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
        private const val FOODID = "FOODID"
        fun show(name: String, image: String, foodId: Int, fragmentManager: FragmentManager) =
            CustomBottomSheetDialogFragment().apply {
                show(fragmentManager, TAG)
                arguments = Bundle().apply {
                    putString(KEY, name)
                    putString(IMAGE, image)
                    putInt(FOODID, foodId)
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
        val foodId = arguments?.getInt(FOODID) ?: 0

        with(binding) {
            timePicker.setIs24HourView(true)
            timePicker.hour = 0
            timePicker.minute = 0
            buttonAdToBuy.setOnClickListener {
                if (checkValidation(buyPrice.text.toString()) && (timePicker.hour != 0 || timePicker.minute != 0)) {
                    val time = String.format(
                        resources.getString(R.string.timeHoursMinutesFormatter), timePicker.hour, timePicker.minute
                    )
                    viewModel.addNewOrder(
                        time,
                        buyPrice.text.toString(),
                        itemName,
                        itemImage,
                        foodId
                    )
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

    // Вынести во вьюмодел
    private fun checkValidation(buyPrice: String): Boolean {
        return (buyPrice.isNotEmpty() && !buyPrice.startsWith("0") && !buyPrice.startsWith("-"))
    }
}