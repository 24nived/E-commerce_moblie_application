package com.example.navasc.viewmodel.factory

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.navasc.viewmodel.IntroductionViewModel
import com.google.firebase.auth.FirebaseAuth

class IntroductionViewModelFactory(
    private val sharedPreferences: SharedPreferences,
    private val firebaseAuth: FirebaseAuth
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(IntroductionViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return IntroductionViewModel(sharedPreferences, firebaseAuth) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
