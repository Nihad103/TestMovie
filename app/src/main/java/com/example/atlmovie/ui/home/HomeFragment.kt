package com.example.atlmovie.ui.home

import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.atlmovie.R
import com.example.atlmovie.adapter.NewReleasesAdapter
import com.example.atlmovie.adapter.OnMovieClickListener
import com.example.atlmovie.adapter.PopularAdapter
import com.example.atlmovie.adapter.TopRatedAdapter
import com.example.atlmovie.adapter.UpcomingAdapter
import com.example.atlmovie.base.BaseFragment
import com.example.atlmovie.databinding.FragmentHomeBinding
import com.example.atlmovie.utils.SeeAllType
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<FragmentHomeBinding>(
    FragmentHomeBinding::inflate
), OnMovieClickListener {

    private val viewModel: HomeViewModel by viewModel()
    private val popularAdapter = PopularAdapter(this)
    private val newReleasesAdapter = NewReleasesAdapter(this)
    private val topRatedAdapter = TopRatedAdapter(this)
    private val upcomingAdapter = UpcomingAdapter(this)

    override fun onViewCreateFinish() {
        observes()
        binding.rvPopular.adapter = popularAdapter
        binding.rvTop10.adapter = topRatedAdapter
        binding.rvUpcoming.adapter = upcomingAdapter
        binding.rvNewReleases.adapter = newReleasesAdapter

        viewModel.getPopularData()
        viewModel.getTopRatedData()
        viewModel.getNewReleasesData()
        viewModel.getUpcomingData()


        binding.btnSeeAllTop10.setOnClickListener {
            val action = HomeFragmentDirections
                .actionHomeFragmentToSeeAllFragment(SeeAllType.POPULAR.name)
            findNavController().navigate(action)
        }

        binding.btnSeeAllPopular.setOnClickListener {
            val action = HomeFragmentDirections
                .actionHomeFragmentToSeeAllFragment(SeeAllType.TOP_RATED.name)
            findNavController().navigate(action)
        }

        binding.btnSeeAllNewReleases.setOnClickListener {
            val action = HomeFragmentDirections
                .actionHomeFragmentToSeeAllFragment(SeeAllType.NEW_RELEASE.name)
            findNavController().navigate(action)
        }

        binding.btnSeeAllUpcoming.setOnClickListener {
            val action = HomeFragmentDirections
                .actionHomeFragmentToSeeAllFragment(SeeAllType.UPCOMING.name)
            findNavController().navigate(action)
        }
    }

    private fun observes() {
        viewModel.popularMovies.observe(viewLifecycleOwner) {
            popularAdapter.updateList(ArrayList(it))
        }

        viewModel.topRatedMovies.observe(viewLifecycleOwner) {
            topRatedAdapter.updateList(ArrayList(it))
        }

        viewModel.upcomingMovies.observe(viewLifecycleOwner) {
            upcomingAdapter.updateList(ArrayList(it))
        }

        viewModel.newReleasesMovies.observe(viewLifecycleOwner) {
            newReleasesAdapter.updateList(ArrayList(it))
        }
        viewModel.popularMovies.observe(viewLifecycleOwner) {
            popularAdapter.updateList(ArrayList(it))
        }
    }

    override fun onMovieClick(movieId: Int) {
        val args = bundleOf("detail" to movieId)
        findNavController().navigate(R.id.action_global_detailFragment, args)
    }
}