package com.example.tedmobchallenge.data

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

class PreferenceManager(context:Context) {

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(
        PREFERENCES_NAME, Context.MODE_PRIVATE
    )
    fun saveUserCredentials(email: String, password: String) {
        sharedPreferences.edit {
            putString(KEY_USER_EMAIL, email)
            putString(KEY_USER_PASSWORD, password)
            putBoolean(KEY_IS_LOGGED_IN, true)
        }
    }
    fun saveUserProfile(firstName: String, lastName: String) {
        sharedPreferences.edit {
            putString(KEY_USER_FIRST_NAME, firstName)
            putString(KEY_USER_LAST_NAME, lastName)
        }
    }

    fun clearUserData() {
        sharedPreferences.edit {
            clear()
        }
    }

    fun isUserLoggedIn(): Boolean {
        return sharedPreferences.getBoolean(KEY_IS_LOGGED_IN, false)
    }

    fun getUserEmail(): String {
        return sharedPreferences.getString(KEY_USER_EMAIL, "") ?: ""
    }

    fun getUserFirstName(): String {
        return sharedPreferences.getString(KEY_USER_FIRST_NAME, "") ?: ""
    }

    fun getUserLastName(): String {
        return sharedPreferences.getString(KEY_USER_LAST_NAME, "") ?: ""
    }


    companion object {
        private const val PREFERENCES_NAME = "tedmob_challenge_prefs"
        private const val KEY_IS_LOGGED_IN = "is_logged_in"
        private const val KEY_USER_EMAIL = "user_email"
        private const val KEY_USER_PASSWORD = "user_password"
        private const val KEY_USER_FIRST_NAME = "user_first_name"
        private const val KEY_USER_LAST_NAME = "user_last_name"
    }
}