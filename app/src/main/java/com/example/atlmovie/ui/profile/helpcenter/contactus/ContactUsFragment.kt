package com.example.atlmovie.ui.profile.helpcenter.contactus

import com.example.atlmovie.R
import com.example.atlmovie.base.BaseFragment
import com.example.atlmovie.databinding.FragmentContactUsBinding
import com.example.atlmovie.model.helpcenter.contact.Contact
import com.example.atlmovie.ui.adapter.helpcenter.contact.ContactAdapter

class ContactUsFragment : BaseFragment<FragmentContactUsBinding>(
    FragmentContactUsBinding::inflate
) {

    val contactAdapter = ContactAdapter()

    override fun onViewCreateFinish() {
        binding.rvContact.adapter=contactAdapter

        contactAdapter.updateList(
            listOf(
                Contact("Customer Service", R.drawable.headphone),
                Contact("Whatsapp",R.drawable.whatsapp),
                Contact("Website", R.drawable.web),
                Contact("Facebook",R.drawable.facered),
                Contact("Instagram",R.drawable.instagram),
                Contact("Twitter",R.drawable.twitter),
            )
        )
    }
}