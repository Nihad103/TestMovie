package com.example.atlmovie.ui.profile.cards

import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.atlmovie.base.BaseFragment
import com.example.atlmovie.databinding.FragmentAddNewBinding


class AddNewFragment : BaseFragment<FragmentAddNewBinding>(
    FragmentAddNewBinding::inflate
) {

    override fun onViewCreateFinish() {
        binding.ivBack.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.btnAdd.setOnClickListener {
            findNavController().popBackStack()
            Toast.makeText(requireContext(), "Card Added" , Toast.LENGTH_SHORT).show()
        }
    }

}