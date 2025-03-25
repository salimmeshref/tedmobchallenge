package com.example.tedmobchallenge.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.createGraph
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.fragment
import com.example.tedmobchallenge.R
import com.example.tedmobchallenge.databinding.ActivityHomeBinding
import com.example.tedmobchallenge.ui.settings.SettingsFragment
import androidx.navigation.ui.setupWithNavController
import com.example.tedmobchallenge.ui.posts.PostsFragment
import com.example.tedmobchallenge.ui.profile.ProfileFragment


class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupToolbar()
        setupNavigation()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
    }

    private fun setupNavigation() {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        navController.graph = navController.createGraph(
            startDestination = R.id.navigation_home
        ) {
            fragment<HomeFragment>(R.id.navigation_home) {
                label = getString(R.string.home)
            }

            fragment<PostsFragment>(R.id.navigation_posts){
                label = getString(R.string.posts)
            }

            fragment<ProfileFragment>(R.id.navigation_profile){
                label = getString(R.string.profile)
            }

            fragment<SettingsFragment>(R.id.navigation_settings) {
                label = getString(R.string.settings)
            }
        }

        binding.bottomNavigation.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            binding.toolbar.title = destination.label
        }
    }
}
