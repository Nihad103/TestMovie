package com.example.atlmovie.ui.notifications

import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.atlmovie.R
import com.example.atlmovie.adapter.NotificationsAdapter
import com.example.atlmovie.adapter.OnMovieClickListener
import com.example.atlmovie.base.BaseFragment
import com.example.atlmovie.databinding.FragmentNotificationsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class NotificationsFragment : BaseFragment<FragmentNotificationsBinding>(
    FragmentNotificationsBinding::inflate
), OnMovieClickListener {

    private val notificationsAdapter = NotificationsAdapter(this)
    private val viewModel: NotificationsViewModel by viewModel()

    override fun onViewCreateFinish() {
        binding.rvNotificatons.adapter = notificationsAdapter
        observes()
        binding.ivBack.setOnClickListener {
            findNavController().popBackStack()
        }

        viewModel.getNotifications()
    }

    override fun onMovieClick(movieId: Int) {
        val args = bundleOf("detail" to movieId)
        findNavController().navigate(R.id.action_global_detailFragment, args)
    }

    private fun observes() {
        viewModel.notifications.observe(viewLifecycleOwner) {
            notificationsAdapter.updateList(ArrayList(it))
        }
        viewModel.isError.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }
    }
}