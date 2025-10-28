package com.example.atlmovie.ui.mylist

import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import com.example.atlmovie.R
import com.example.atlmovie.base.BaseFragment
import com.example.atlmovie.databinding.FragmentMyListBinding
import com.example.atlmovie.ui.adapter.OnMovieClickListener
import com.example.atlmovie.ui.adapter.mylist.MyListAdapter
import com.example.atlmovie.utils.gone
import com.example.atlmovie.utils.visible
import org.koin.androidx.viewmodel.ext.android.viewModel

class MyListFragment : BaseFragment<FragmentMyListBinding>(
    FragmentMyListBinding::inflate
), OnMovieClickListener {

    private val viewModel: MyListViewModel by viewModel()
    private lateinit var adapter: MyListAdapter

    override fun onViewCreateFinish() {
        setupRecyclerView()
        observes()
        viewModel.fetchMyListMovies()

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
        adapter = MyListAdapter(
            listener = this,
            onDeleteClick = { movie ->
                viewModel.deleteMovie(movie)
                Toast.makeText(
                    requireContext(),
                    "${movie.title} deleted",
                    Toast.LENGTH_SHORT).show()
            }
        )
        binding.rvMyList.adapter = adapter
    }

    private fun observes() {
        viewModel.myListMovies.observe(viewLifecycleOwner) { list ->
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
