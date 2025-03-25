package com.example.tedmobchallenge.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tedmobchallenge.R
import com.example.tedmobchallenge.data.PreferenceManager
import com.example.tedmobchallenge.databinding.ActivityLoginBinding
import com.example.tedmobchallenge.ui.home.HomeActivity

class LoginActivity : AppCompatActivity() {


    private lateinit var binding: ActivityLoginBinding
    private lateinit var preferencesManager: PreferenceManager


    private fun setupUI() {
        binding.btnLogin.setOnClickListener {
            validateAndLogin()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        preferencesManager = PreferenceManager(this)

        setupUI()
    }

    private fun validateAndLogin() {
        val email = binding.email.text.toString().trim()
        val password = binding.password.text.toString().trim()

        if (email.isEmpty()) {
            binding.inputEmail.error = getString(R.string.required)
            return
        }

        if (password.isEmpty()) {
            binding.inputPassword.error = getString(R.string.required)
            return
        }

        preferencesManager.saveUserCredentials(email, password)

        preferencesManager.saveUserProfile("TED", "MOB")

        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }
}