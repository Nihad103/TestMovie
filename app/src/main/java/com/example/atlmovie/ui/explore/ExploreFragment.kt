package com.example.atlmovie.ui.explore

import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import com.example.atlmovie.R
import com.example.atlmovie.base.BaseFragment
import com.example.atlmovie.databinding.FragmentExploreBinding
import com.example.atlmovie.ui.adapter.MoviesAdapter
import com.example.atlmovie.ui.adapter.OnMovieClickListener
import com.example.atlmovie.ui.adapter.search.SearchMoviesAdapter
import com.example.atlmovie.utils.gone
import com.example.atlmovie.utils.visible
import org.koin.androidx.viewmodel.ext.android.viewModel

class ExploreFragment : BaseFragment<FragmentExploreBinding>(
    FragmentExploreBinding::inflate
), OnMovieClickListener {

    private val adapter = MoviesAdapter(this)
    private val searchMoviesAdapter = SearchMoviesAdapter(this)
    private val viewModel: ExploreViewModel by viewModel()

    override fun onViewCreateFinish() {
        binding.rvExplore.adapter = adapter
        binding.rvExploreSearch.adapter = searchMoviesAdapter

        setupSearchListener()
        observes()

        viewModel.getPopularData()
    }

    private fun observes() {
        viewModel.movies.observe(viewLifecycleOwner) {
            adapter.updateList(ArrayList(it))
        }
        viewModel.searchMovies.observe(viewLifecycleOwner) {
            searchMoviesAdapter.updateList(it)
        }
        viewModel.isError.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onMovieClick(movieId: Int) {
        val args = bundleOf("detail" to movieId)
        findNavController().navigate(R.id.action_global_detailFragment, args)
    }

    private fun setupSearchListener() {
        binding.etSearch.addTextChangedListener { editable ->
            val query = editable.toString().trim()

            if (query.isEmpty()) {
                binding.rvExplore.visible()
                binding.rvExploreSearch.gone()
                binding.ivError404.gone()
            } else {
                viewModel.searchMovies(query)

                viewModel.searchMovies.observe(viewLifecycleOwner) { result ->
                    if (result.isNullOrEmpty()) {
                        binding.rvExploreSearch.gone()
                        binding.rvExplore.gone()
                        binding.ivError404.visible()
                    } else {
                        binding.rvExploreSearch.visible()
                        binding.rvExplore.gone()
                        binding.ivError404.gone()
                        searchMoviesAdapter.updateList(result)
                    }
                }
            }
        }
    }
}