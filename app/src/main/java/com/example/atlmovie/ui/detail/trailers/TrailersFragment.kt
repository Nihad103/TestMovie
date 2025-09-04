package com.example.atlmovie.ui.detail.trailers

import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.atlmovie.R
import com.example.atlmovie.adapter.OnMovieClickListener
import com.example.atlmovie.adapter.pager.TrailersAdapter
import com.example.atlmovie.base.BaseFragment
import com.example.atlmovie.databinding.FragmentTrailersBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class TrailersFragment : BaseFragment<FragmentTrailersBinding>(
    FragmentTrailersBinding::inflate
) , OnMovieClickListener {

    private val viewModel: TrailersViewModel by viewModel()
    private val trailersAdapter = TrailersAdapter(this)

    override fun onViewCreateFinish() {
        observes()
        binding.rvTrailers.adapter = trailersAdapter
        viewModel.getPopularData()
    }

    private fun observes() {
        viewModel.popularMovies.observe(viewLifecycleOwner) {
            trailersAdapter.updateList(ArrayList(it))
        }
        viewModel.popularMovies.observe(viewLifecycleOwner) {
            trailersAdapter.updateList(ArrayList(it))
        }
    }

    override fun onMovieClick(movieId: Int) {
        val args = bundleOf("detail" to movieId)
        findNavController().navigate(R.id.action_global_detailFragment, args)}

}