package com.example.atlmovie.ui.detail.morelikethis

import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.atlmovie.R
import com.example.atlmovie.adapter.OnMovieClickListener
import com.example.atlmovie.adapter.pager.MoreLikeThisAdapter
import com.example.atlmovie.base.BaseFragment
import com.example.atlmovie.databinding.FragmentMoreLikeThisBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MoreLikeThisFragment : BaseFragment<FragmentMoreLikeThisBinding>(
    FragmentMoreLikeThisBinding::inflate
), OnMovieClickListener {

    private val viewModel: MoreLikeThisViewModel by viewModel()
    private val moreLikeThisAdapter = MoreLikeThisAdapter(this)

    override fun onViewCreateFinish() {
        observes()
        binding.rvMoreLikeThis.adapter = moreLikeThisAdapter
        viewModel.getPopularData()
    }

    private fun observes() {
        viewModel.popularMovies.observe(viewLifecycleOwner) {
            moreLikeThisAdapter.updateList(ArrayList(it))
        }
        viewModel.popularMovies.observe(viewLifecycleOwner) {
            moreLikeThisAdapter.updateList(ArrayList(it))
        }
        viewModel.isError.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onMovieClick(movieId: Int) {
        val args = bundleOf("detail" to movieId)
        findNavController().navigate(R.id.action_global_detailFragment, args)
    }
}