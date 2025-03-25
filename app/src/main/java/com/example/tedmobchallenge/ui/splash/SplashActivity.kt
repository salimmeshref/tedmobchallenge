package com.example.tedmobchallenge.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.tedmobchallenge.R
import com.example.tedmobchallenge.ui.home.HomeActivity
import com.example.tedmobchallenge.ui.login.LoginActivity
import data.PreferenceManager

class SplashActivity : AppCompatActivity() {

    private lateinit var preferencesManager: PreferenceManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        preferencesManager = PreferenceManager(this)

        Handler(Looper.getMainLooper()).postDelayed({
            nextScreen()
        },1500)
    }


    private fun nextScreen() {
        val intent = if (preferencesManager.isUserLoggedIn()) {
            Intent(this, HomeActivity::class.java)
        } else {
            Intent(this, LoginActivity::class.java)
        }

        startActivity(intent)
        finish()
    }
}