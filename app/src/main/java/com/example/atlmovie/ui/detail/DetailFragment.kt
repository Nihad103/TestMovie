package com.example.atlmovie.ui.detail

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.atlmovie.adapter.PeopleAdapter
import com.example.atlmovie.adapter.pager.DetailPagerAdapter
import com.example.atlmovie.base.BaseFragment
import com.example.atlmovie.databinding.FragmentDetailBinding
import com.example.atlmovie.service.MovieRepository
import com.example.atlmovie.ui.home.HomeViewModel
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : BaseFragment<FragmentDetailBinding>(
    FragmentDetailBinding::inflate
) {

    val animalsArray = arrayOf(
        "Cat",
        "Dog",
        "Bird"
    )

    private val viewModel: DetailViewModel by viewModel()
    private val args: DetailFragmentArgs by navArgs()
    private val peopleAdapter = PeopleAdapter()

    override fun onViewCreateFinish() {
        tabLayoutAndPager()
        observes()
        viewModel.fetchMovieDetail(args.detail)
        binding.rvPeople.adapter = peopleAdapter
        binding.ivBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun observes() {
        viewModel.movieDetail.observe(viewLifecycleOwner) {
            binding.detail = it
        }
        viewModel.error.observe(viewLifecycleOwner) { error ->
            Log.e("DetailFragment", error)
        }
    }

    private fun tabLayoutAndPager() {
        // Adapteri viewPager-ə təyin et
        val viewPager = binding.tabviewPager2
        val tabLayout = binding.tabLayout
        val adapter = DetailPagerAdapter(childFragmentManager, lifecycle)
        viewPager.adapter = adapter

        // TabLayout ilə ViewPager-i birləşdir
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "Trailers"
                1 -> "More Like This"
                else -> ""
            }
        }.attach()
    }


}