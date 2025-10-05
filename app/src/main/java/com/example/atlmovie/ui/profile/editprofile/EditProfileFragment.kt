package com.example.atlmovie.ui.profile.editprofile

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.atlmovie.R
import com.example.atlmovie.base.BaseFragment
import com.example.atlmovie.databinding.FragmentEditProfileBinding
import com.example.atlmovie.utils.Prefs
import com.example.atlmovie.utils.gone
import com.example.atlmovie.utils.visible
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.File

class EditProfileFragment : BaseFragment<FragmentEditProfileBinding>(
    FragmentEditProfileBinding::inflate
) {

    private lateinit var pickImageLauncher: ActivityResultLauncher<Intent>
    private val firebaseAuth = FirebaseAuth.getInstance()
    private var selectedImagePath: String? = null

    override fun onViewCreateFinish() {
        setupImagePicker()
        loadUserData()

        binding.btnEdit.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            pickImageLauncher.launch(intent)
        }

        binding.ivBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.countryCodePicker.registerCarrierNumberEditText(binding.editTextPhone)

        val genderList = listOf("Male", "Female", "Other")
        val genderAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_item, genderList)
        binding.autoCompleteGender.setAdapter(genderAdapter)
        binding.autoCompleteGender.setDropDownBackgroundResource(R.color.grey_bd)

        val countries = listOf(
            "Afghanistan", "Albania", "Algeria", "Andorra", "Angola",
            "Argentina", "Azerbaijan", "Australia", "Austria",
            "Bahamas", "Bangladesh", "Belarus", "Turkey"
        )
        val countryAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_item, countries)
        binding.autoCompleteCountry.setAdapter(countryAdapter)
        binding.autoCompleteCountry.setDropDownBackgroundResource(R.color.grey_bd)

        binding.btnUpdate.setOnClickListener {
            lifecycleScope.launch {
                binding.animationView22.visible()
                delay(1500)
                binding.animationView22.gone()
                saveUserData()
            }
        }
    }

    private fun setupImagePicker() {
        pickImageLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val uri = result.data?.data ?: return@registerForActivityResult
                val path = saveImageToInternalStorage(uri)
                if (path != null) {
                    selectedImagePath = path
                    binding.ivEditProfile.setImageURI(Uri.fromFile(File(path)))
                    Prefs.setProfileImagePath(requireContext(), path)
                }
            }
        }
    }

    private fun saveImageToInternalStorage(uri: Uri): String? {
        return try {
            val inputStream = requireContext().contentResolver.openInputStream(uri)
            val file = File(requireContext().filesDir, "profile_image.jpg")
            val outputStream = file.outputStream()
            inputStream?.copyTo(outputStream)
            inputStream?.close()
            outputStream.close()
            file.absolutePath
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    private fun loadUserData() {
        val currentUser = firebaseAuth.currentUser
        binding.etEmail.setText(currentUser?.email ?: "")
        binding.etEmail.isEnabled = false
        binding.etNickName.setText(Prefs.getUserName(requireContext()))
        binding.autoCompleteCountry.setText(Prefs.getUserCountry(requireContext()), false)
        binding.autoCompleteGender.setText(Prefs.getUserGender(requireContext()), false)
        binding.editTextPhone.setText(Prefs.getUserPhone(requireContext()))

        val savedPath = Prefs.getProfileImagePath(requireContext())
        val file = savedPath?.let { File(it) }
        if (file?.exists() == true) {
            binding.ivEditProfile.setImageURI(Uri.fromFile(file))
            selectedImagePath = savedPath
        } else {
            binding.ivEditProfile.setImageResource(R.drawable.default_profile_photo)
        }
    }

    private fun saveUserData() {
        Prefs.setUserName(requireContext(), binding.etNickName.text.toString())
        Prefs.setUserCountry(requireContext(), binding.autoCompleteCountry.text.toString())
        Prefs.setUserGender(requireContext(), binding.autoCompleteGender.text.toString())
        Prefs.setUserPhone(requireContext(), binding.editTextPhone.text.toString())
        selectedImagePath?.let { Prefs.setProfileImagePath(requireContext(), it) }

        Toast.makeText(requireContext(), "Data saved successfully", Toast.LENGTH_SHORT).show()
    }

    override fun onResume() {
        super.onResume()
        loadUserData()
    }
}