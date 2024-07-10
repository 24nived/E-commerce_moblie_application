package com.example.navasc.viewmodel.factory


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.navasc.viewmodel.MainCategoryViewModel
import com.google.firebase.firestore.FirebaseFirestore

class MainCategoryViewModelFactory(
    private val firestore: FirebaseFirestore
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainCategoryViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainCategoryViewModel(firestore) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
