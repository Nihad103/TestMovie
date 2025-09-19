package com.example.atlmovie.ui

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.atlmovie.R
import com.example.atlmovie.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        bottomNav()
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);
    }

    private fun bottomNav() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController
        binding.bottomNav.setupWithNavController(navController)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            binding.root.post {
                binding.bottomNav.isVisible = when (destination.id) {
                    R.id.homeFragment,
                    R.id.exploreFragment,
                    R.id.myListFragment,
                    R.id.profileFragment -> true
                    else -> false
                }
//                if (destination.id == R.id.termsAndConditionsFragment) {
//                    binding.bottomNav.menu.findItem(R.id.profileFragment).isChecked = true
//                }
            }
        }
    }
}