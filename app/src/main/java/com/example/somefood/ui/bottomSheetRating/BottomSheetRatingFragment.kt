package com.example.somefood.ui.bottomSheetRating

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.somefood.R
import com.example.somefood.databinding.FragmentBottomSheetRatingBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class BottomSheetRatingFragment : BottomSheetDialogFragment() {

    companion object {
        private const val TAG = "addRating"
        private const val ARG_USERID = "USERID"
        private const val ARG_ORDERID = "ORDERID"
        fun show(userIdToRating: Int, orderId: Int, fragmentManager: FragmentManager) =
            BottomSheetRatingFragment().apply {
                show(fragmentManager, TAG)
                arguments = Bundle().apply {
                    putInt(ARG_USERID, userIdToRating)
                    putInt(ARG_ORDERID, orderId)
                }
            }
    }

    private val viewModel: BottomSheetRatingViewModel by viewModel()
    private val binding: FragmentBottomSheetRatingBinding by viewBinding()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_bottom_sheet_rating, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val userIdToRating = arguments?.getInt(ARG_USERID)
        val orderId = arguments?.getInt(ARG_ORDERID) ?: 0

        with(binding) {
            addStarButton.setOnClickListener {
                viewModel.goRatingUser(userIdToRating, ratingBar.rating.toDouble(), orderId)
                dialog?.dismiss()
            }
        }
    }
}