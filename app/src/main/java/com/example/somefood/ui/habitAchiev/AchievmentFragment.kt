package com.example.somefood.ui.habitAchiev

import android.graphics.Color
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.somefood.R
import com.example.somefood.databinding.FragmentAchievmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class AchievmentFragment : Fragment(R.layout.fragment_achievment) {

    companion object {
        fun newInstance() = AchievmentFragment()
    }

    private val binding: FragmentAchievmentBinding by viewBinding()
    private val viewModel: AchievmentViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttawdawd.setBackgroundColor(Color.GREEN)

    }

}