//package com.example.navasc.viewmodel.factory
//
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.ViewModelProvider
//import com.example.navasc.data.Category
//import com.example.navasc.viewmodel.CategoryViewModel
//import com.google.firebase.firestore.FirebaseFirestore
//
//class BaseCategoryViewModelFactory(
//    private val firestore: FirebaseFirestore,
//    private val category: Category
//):ViewModelProvider.Factory {
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        return CategoryViewModel(firestore, category) as T
//    }
//}
package com.example.navasc.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.navasc.data.Category
import com.example.navasc.viewmodel.CategoryViewModel
import com.google.firebase.firestore.FirebaseFirestore

class BaseCategoryViewModelFactory(
    private val firestore: FirebaseFirestore,
    private val category: Category
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CategoryViewModel::class.java)) {
            return CategoryViewModel(firestore, category) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
