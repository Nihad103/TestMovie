package com.example.atlmovie.ui.profile

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.provider.MediaStore
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.fragment.findNavController
import com.example.atlmovie.R
import com.example.atlmovie.base.BaseFragment
import com.example.atlmovie.databinding.FragmentProfileBinding
import com.example.atlmovie.utils.Prefs
import com.google.android.material.bottomsheet.BottomSheetDialog
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment : BaseFragment<FragmentProfileBinding>(
    FragmentProfileBinding::inflate
) {
    private val viewModel: ProfileViewModel by viewModel()
    private lateinit var pickImageLauncher: ActivityResultLauncher<Intent>

    override fun onViewCreateFinish() {
        observes()

        binding.constraintLayoutNotifications.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_notificationSettingsFragment)
        }
        binding.constraintLayoutEditProfile.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_editProfileFragment)
        }
        binding.constraintLayoutPrivacy.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_privacyFragment)
        }
        binding.constrainthelpcenter.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_helpCenterFragment)
        }
        binding.materialCardView.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_subscribeToPremiumFragment)
        }


        binding.tvUserEmail.text = Prefs.getUserEmail(requireContext())

        binding.switch1.isChecked = Prefs.isDarkMode(requireContext())

        binding.switch1.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                Prefs.setDarkMode(requireContext(), true)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                Prefs.setDarkMode(requireContext(), false)
            }
        }

        loadProfileImage()

        pickImageLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
                val uri = data?.data
                if (uri != null) {
                    val path = saveImageToInternalStorage(uri)
                    Prefs.setProfileImagePath(requireContext(), path)
                    binding.ivProfile.setImageURI(android.net.Uri.fromFile(java.io.File(path)))
                }
            }
        }

        binding.btnEdit.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            pickImageLauncher.launch(intent)
        }

        binding.constraintLayoutLogout.setOnClickListener {
            showLogoutBottomSheet()
        }

        binding.constraintLayoutLanguage.setOnClickListener {
            setLanguage()
        }
    }

    private fun setLanguage() {
        if (binding.constraintLayoutLanguage.isClickable) {
            binding.constraintLayoutLanguage.isClickable = false
        }
        val bottomSheet = BottomSheetDialog(requireContext())
        val bottomSheetView = layoutInflater.inflate(R.layout.bottom_sheet, null)
        bottomSheet.setContentView(bottomSheetView)

        val pref = requireContext().getSharedPreferences("MyPrefs", Activity.MODE_PRIVATE)

        bottomSheetView.findViewById<View>(R.id.langEnglish).setOnClickListener {
            changeLanguage("en", pref)
            bottomSheet.dismiss()
        }
        bottomSheetView.findViewById<View>(R.id.langAzerbaijani).setOnClickListener {
            changeLanguage("az", pref)
            bottomSheet.dismiss()
        }
        bottomSheetView.findViewById<View>(R.id.langTurkish).setOnClickListener {
            changeLanguage("tr", pref)
            bottomSheet.dismiss()
        }
        bottomSheetView.findViewById<View>(R.id.langRussian).setOnClickListener {
            changeLanguage("ru", pref)
            bottomSheet.dismiss()
        }
        bottomSheet.show()
    }

    private fun changeLanguage(langCode: String, prefs: SharedPreferences) {
        prefs.edit().putString("app_language", langCode).apply()
        requireActivity().recreate()
    }

    private fun observes() {
        viewModel.logoutEvent.observe(viewLifecycleOwner) {
            if (it) {
                Prefs.setSavedLogin(requireContext(), false)

                findNavController().navigate(R.id.action_profileFragment_to_loginFragment)
            }
        }
    }

    private fun saveImageToInternalStorage(uri: android.net.Uri): String {
        val inputStream = requireContext().contentResolver.openInputStream(uri)
        val file = java.io.File(requireContext().filesDir, "profile.jpg")
        val outputStream = java.io.FileOutputStream(file)

        inputStream?.copyTo(outputStream)
        inputStream?.close()
        outputStream.close()

        return file.absolutePath
    }

    private fun loadProfileImage() {
        val imagePath = Prefs.getProfileImagePath(requireContext())
        if (imagePath != null) {
            val file = java.io.File(imagePath)
            if (file.exists()) {
                binding.ivProfile.setImageURI(android.net.Uri.fromFile(file))
            }
        }
    }

    private fun showLogoutBottomSheet() {
        val bottomSheet = BottomSheetDialog(requireContext())
        val bottomSheetView = layoutInflater.inflate(R.layout.alert_dialog, null)
        bottomSheet.setContentView(bottomSheetView)

        val btnCancel = bottomSheetView.findViewById<View>(R.id.btnCancel)
        val btnLogout = bottomSheetView.findViewById<View>(R.id.btnLogout)

        btnCancel.setOnClickListener {
            bottomSheet.dismiss()
        }
        btnLogout.setOnClickListener {
            bottomSheet.dismiss()
            viewModel.logout()
            Prefs.setSavedLogin(requireContext(), false)
            findNavController().navigate(R.id.action_profileFragment_to_loginFragment)
        }
        bottomSheet.show()
    }

}