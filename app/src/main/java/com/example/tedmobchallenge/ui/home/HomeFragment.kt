package com.example.tedmobchallenge.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tedmobchallenge.R
import com.example.tedmobchallenge.data.PreferenceManager
import com.example.tedmobchallenge.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var preferencesManager : PreferenceManager


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        preferencesManager = PreferenceManager(requireContext())
        setupUI()
    }

    private fun setupUI() {
        val userEmail = preferencesManager.getUserEmail()
        binding.welcomeMessage.text = getString(R.string.welcome_user, userEmail)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}