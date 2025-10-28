package com.example.atlmovie.ui.detail.trailers

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import com.example.atlmovie.base.BaseFragment
import com.example.atlmovie.databinding.FragmentTrailersBinding
import com.example.atlmovie.ui.adapter.detail.pager.TrailersAdapter
import com.example.atlmovie.ui.adapter.detail.trailerclickinterface.OnTrailerClickListener
import org.koin.androidx.viewmodel.ext.android.viewModel

class TrailersFragment : BaseFragment<FragmentTrailersBinding>(
    FragmentTrailersBinding::inflate
) , OnTrailerClickListener {

    private val viewModel: TrailersViewModel by viewModel()
    private val trailersAdapter = TrailersAdapter(this)

    private var movieId: Int = 0

    override fun onViewCreateFinish() {
        movieId = arguments?.getInt("movieId") ?: 0
        binding.rvTrailers.adapter = trailersAdapter
        observes()
        viewModel.fetchTrailers(movieId)
    }

    private fun observes() {
        viewModel.trailers.observe(viewLifecycleOwner) { trailers ->
            trailersAdapter.updateList(trailers)
        }
        viewModel.isError.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        fun newInstance(movieId: Int): TrailersFragment {
            val fragment = TrailersFragment()
            fragment.arguments = Bundle().apply {
                putInt("movieId", movieId)
            }
            return fragment
        }
    }

    override fun onTrailerClick(key: String) {
        val youtubeUrl = "https://www.youtube.com/watch?v=$key"
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(youtubeUrl))
        startActivity(intent)
    }
}