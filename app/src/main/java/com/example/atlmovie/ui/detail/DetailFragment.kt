package com.example.atlmovie.ui.detail

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.atlmovie.R
import com.example.atlmovie.adapter.PeopleAdapter
import com.example.atlmovie.adapter.pager.DetailPagerAdapter
import com.example.atlmovie.base.BaseFragment
import com.example.atlmovie.databinding.FragmentDetailBinding
import com.example.atlmovie.model.download.DownloadEntity
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : BaseFragment<FragmentDetailBinding>(
    FragmentDetailBinding::inflate
) {

    private val viewModel: DetailViewModel by viewModel()
    private val args: DetailFragmentArgs by navArgs()
    private val peopleAdapter = PeopleAdapter()

    private var downloadDialog: AlertDialog? = null
//    private var downloadJob: Job? = null

    override fun onViewCreateFinish() {
        tabLayoutAndPager()
        observes()
        viewModel.fetchMovieDetail(args.detail)
        binding.rvPeople.adapter = peopleAdapter
        binding.ivBack.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.btnBookmark.setOnClickListener {
            viewModel.toggleBookmark()
        }
        binding.btnDownload.setOnClickListener {
            startDownload()
        }
    }

    private fun observes() {
        viewModel.movieDetail.observe(viewLifecycleOwner) {
            binding.detail = it
        }

        viewModel.error.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }

        viewModel.isInMyList.observe(viewLifecycleOwner) { inList ->
            if (inList) {
                binding.btnBookmark.setColorFilter(resources.getColor(R.color.red_21))
            } else {
                binding.btnBookmark.setColorFilter(resources.getColor(R.color.grey_21))
            }
        }

        viewModel.toastMessage.observe(viewLifecycleOwner) { message ->
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        }
    }

    private fun tabLayoutAndPager() {
        val viewPager = binding.tabviewPager2
        val tabLayout = binding.tabLayout
        val adapter = DetailPagerAdapter(childFragmentManager, lifecycle, args.detail)
        viewPager.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "Trailers"
                1 -> "More Like This"
                else -> ""
            }
        }.attach()
    }

    private fun startDownload() {
        val movie = viewModel.movieDetail.value ?: return

        if (downloadDialog?.isShowing == true) return

        val dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.alert_download, null)
        val progressBar = dialogView.findViewById<ProgressBar>(R.id.progressBar)
        val progressText = dialogView.findViewById<TextView>(R.id.tvProgressDegree)
        val statusText = dialogView.findViewById<TextView>(R.id.tvStatus)
        val hideButton = dialogView.findViewById<Button>(R.id.hideButton)

        downloadDialog = AlertDialog.Builder(requireContext())
            .setView(dialogView)
            .setCancelable(false)
            .create()
        downloadDialog?.show()

        val downloadJob = viewLifecycleOwner.lifecycleScope.launch {
            for (i in 1..100) {
                if (!isActive) break
                delay(50)
                progressBar.progress = i
                progressText.text = "$i%"
                statusText.text = if (i < 100) "Downloading..." else "Download Complete"
            }

            if (isActive && downloadDialog?.isShowing == true) {
                val downloadEntity = DownloadEntity(
                    id = movie.id,
                    adult = movie.adult,
                    backdropPath = movie.backdropPath,
                    originalLanguage = movie.originalLanguage,
                    originalTitle = movie.originalTitle,
                    overview = movie.overview,
                    popularity = movie.popularity,
                    posterPath = movie.posterPath,
                    releaseDate = movie.releaseDate,
                    title = movie.title,
                    video = movie.video,
                    voteAverage = movie.voteAverage,
                    voteCount = movie.voteCount
                )

                withContext(Dispatchers.IO) {
                    val downloadEntity = movie
                    viewModel.addMovieToDownload(downloadEntity)
                }

                Toast.makeText(requireContext(), "${movie.title} downloaded!", Toast.LENGTH_SHORT).show()
                downloadDialog?.dismiss()
            }
        }

        hideButton.setOnClickListener {
            downloadJob.cancel()
            downloadDialog?.dismiss()
            Toast.makeText(requireContext(), "Download Cancelled", Toast.LENGTH_SHORT).show()
        }
    }
}