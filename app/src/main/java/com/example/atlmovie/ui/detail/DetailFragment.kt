package com.example.atlmovie.ui.detail

import android.util.Log
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.atlmovie.adapter.PeopleAdapter
import com.example.atlmovie.adapter.pager.DetailPagerAdapter
import com.example.atlmovie.base.BaseFragment
import com.example.atlmovie.databinding.FragmentDetailBinding
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : BaseFragment<FragmentDetailBinding>(
    FragmentDetailBinding::inflate
) {

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
        viewModel.error.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
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
}