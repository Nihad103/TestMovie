package com.example.atlmovie.ui.profile.helpcenter.faq

import com.example.atlmovie.R
import com.example.atlmovie.base.BaseFragment
import com.example.atlmovie.databinding.FragmentFaqBinding
import com.example.atlmovie.model.helpcenter.faq.Faq
import com.example.atlmovie.ui.adapter.helpcenter.faq.FaqAdapter
import com.example.atlmovie.utils.gone
import com.example.atlmovie.utils.visible


class FaqFragment : BaseFragment<FragmentFaqBinding>(
    FragmentFaqBinding::inflate
) {

    val faqAdapter = FaqAdapter()

    override fun onViewCreateFinish() {

        faqAdapter.onClick = { binding, item, _ ->
            if (item.isExpanded) {
                binding.tvAnswer.visible()
            } else {
                binding.tvAnswer.gone()
            }
        }
        binding.rvFaq.adapter=faqAdapter

        faqAdapter.updateList(
            listOf(
                Faq("What is Mova?", getString(R.string.what_is_Mova)),
                Faq("How do I add content to the list?",getString(R.string.how_list)),
                Faq("How to remove wishlist?",getString(R.string.how_to_remove_wishlist)),
                Faq("How do I subscribe to premium?",getString(R.string.how_premium)),
                Faq("How do I can download movies?",getString(R.string.how_download)),
                Faq("How to unsubscribe from premium?",getString(R.string.how_unsubrice)),
                Faq("How do I add content to the list?",getString(R.string.how_list)),
                Faq("How to remove wishlist?",getString(R.string.how_to_remove_wishlist)),
                Faq("How do I subscribe to premium?",getString(R.string.how_premium)),
                Faq("How do I can download movies?",getString(R.string.how_download)),
                Faq("How to unsubscribe from premium?",getString(R.string.how_unsubrice)),
                Faq("How do I add content to the list?",getString(R.string.how_list)),
                Faq("How to remove wishlist?",getString(R.string.how_to_remove_wishlist)),
                Faq("How do I subscribe to premium?",getString(R.string.how_premium)),
                Faq("How do I can download movies?",getString(R.string.how_download)),
                Faq("How to unsubscribe from premium?",getString(R.string.how_unsubrice)),
                Faq("How do I add content to the list?",getString(R.string.how_list)),
                Faq("How to remove wishlist?",getString(R.string.how_to_remove_wishlist)),
            )
        )


    }
}