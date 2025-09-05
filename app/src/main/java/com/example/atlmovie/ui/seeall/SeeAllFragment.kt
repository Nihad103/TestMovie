package com.example.atlmovie.ui.seeall

import androidx.core.os.bundleOf
import androidx.lifecycle.createSavedStateHandle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.atlmovie.R
import com.example.atlmovie.adapter.MoviesAdapter
import com.example.atlmovie.adapter.OnMovieClickListener
import com.example.atlmovie.base.BaseFragment
import com.example.atlmovie.databinding.FragmentSeeAllBinding
import com.example.atlmovie.utils.SeeAllType
import org.koin.androidx.viewmodel.ext.android.viewModel

class SeeAllFragment : BaseFragment<FragmentSeeAllBinding>(
    FragmentSeeAllBinding::inflate
), OnMovieClickListener {

    private val adapter = MoviesAdapter(this)
    private val viewModel: SeeAllViewModel by viewModel()
    private val args: SeeAllFragmentArgs by navArgs()

    override fun onViewCreateFinish() {
        binding.rvSeeAll.adapter = adapter

        viewModel.movies.observe(viewLifecycleOwner) {
            adapter.updateList(it)
        }
        binding.ivBack.setOnClickListener {
            findNavController().popBackStack()
        }

        when (args.listType) {
            SeeAllType.POPULAR.name -> binding.tvTitle.text = "Popular"
            SeeAllType.TOP_RATED.name -> binding.tvTitle.text = "Top Rated"
            SeeAllType.NEW_RELEASE.name -> binding.tvTitle.text = "New Releases"
            SeeAllType.UPCOMING.name -> binding.tvTitle.text = "Upcoming"
        }
    }

    override fun onMovieClick(movieId: Int) {
        val args = bundleOf("detail" to movieId)
        findNavController().navigate(R.id.action_global_detailFragment, args)}
    }