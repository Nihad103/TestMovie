package com.example.atlmovie.ui.seeall

import androidx.core.os.bundleOf
import androidx.lifecycle.createSavedStateHandle
import androidx.navigation.fragment.findNavController
import com.example.atlmovie.R
import com.example.atlmovie.adapter.MoviesAdapter
import com.example.atlmovie.adapter.OnMovieClickListener
import com.example.atlmovie.base.BaseFragment
import com.example.atlmovie.databinding.FragmentSeeAllBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class SeeAllFragment : BaseFragment<FragmentSeeAllBinding>(
    FragmentSeeAllBinding::inflate
), OnMovieClickListener {

    private val adapter = MoviesAdapter(this)
    private val viewModel: SeeAllViewModel by viewModel()

    override fun onViewCreateFinish() {
        binding.rvSeeAll.adapter = adapter

        viewModel.movies.observe(viewLifecycleOwner) {
            adapter.updateList(it)
        }
        binding.ivBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun onMovieClick(movieId: Int) {
        val args = bundleOf("detail" to movieId)
        findNavController().navigate(R.id.action_global_detailFragment, args)}
    }