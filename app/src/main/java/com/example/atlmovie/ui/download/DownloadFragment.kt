package com.example.atlmovie.ui.download

import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import com.example.atlmovie.R
import com.example.atlmovie.adapter.DownloadAdapter
import com.example.atlmovie.adapter.OnMovieClickListener
import com.example.atlmovie.base.BaseFragment
import com.example.atlmovie.databinding.FragmentMyListBinding
import com.example.atlmovie.utils.gone
import com.example.atlmovie.utils.visible
import org.koin.androidx.viewmodel.ext.android.viewModel

class DownloadFragment : BaseFragment<FragmentMyListBinding>(
    FragmentMyListBinding::inflate
), OnMovieClickListener {

    private val viewModel: DownloadViewModel by viewModel()
    private lateinit var adapter: DownloadAdapter

    override fun onViewCreateFinish() {
        setupRecyclerView()
        observes()
        viewModel.fetchDownloadMovies()

        binding.imgSearch.setOnClickListener {
            if (binding.etSearchMyList.isVisible) {
                binding.etSearchMyList.gone()
                binding.tvTitle.visible()
                binding.ivTitle.visible()
            } else {
                binding.etSearchMyList.visible()
                binding.tvTitle.gone()
                binding.ivTitle.gone()
            }
        }

        val searchEditText = binding.etSearchMyList.editText
        searchEditText?.addTextChangedListener { text ->
            adapter.filter(text.toString())
        }
    }

    private fun setupRecyclerView() {
        adapter = DownloadAdapter(
            listener = this,
            onDeleteClick = { movie ->
                viewModel.deleteMovie(movie)
                Toast.makeText(
                    requireContext(),
                    "${movie.title} deleted from downloads",
                    Toast.LENGTH_SHORT
                ).show()
            }
        )
        binding.rvMyList.adapter = adapter
    }

    private fun observes() {
        viewModel.downloadMovies.observe(viewLifecycleOwner) { list ->
            if (list.isNullOrEmpty()) {
                binding.rvMyList.gone()
                binding.ivError404.visible()
            } else {
                binding.rvMyList.visible()
                binding.ivError404.gone()
                adapter.updateList(list)
            }
        }

        viewModel.error.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onMovieClick(movieId: Int) {
        val args = bundleOf("detail" to movieId)
        findNavController().navigate(R.id.action_global_detailFragment, args)
    }
}
