//package com.example.navasc.fragments.fragments.categories
//
//import android.os.Bundle
//import android.view.View
//import androidx.fragment.app.viewModels
//import androidx.lifecycle.lifecycleScope
//import com.example.navasc.data.Category
//import com.example.navasc.util.Resource
//import com.example.navasc.viewmodel.CategoryViewModel
//import com.example.navasc.viewmodel.factory.BaseCategoryViewModelFactory
//import com.google.android.material.snackbar.Snackbar
//import com.google.firebase.firestore.FirebaseFirestore
//import dagger.hilt.android.AndroidEntryPoint
//import kotlinx.coroutines.flow.collectLatest
//import javax.inject.Inject
//
//
//@AndroidEntryPoint
//class FrockFragment:BaseCategoryFragment() {
//
//    @Inject
//    lateinit var firestore: FirebaseFirestore
//
//    val  viewModel by viewModels<CategoryViewModel> {
//        BaseCategoryViewModelFactory(firestore, Category.Frock)
//    }
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        lifecycleScope.launchWhenStarted {
//            viewModel.offerProducts.collectLatest {
//                when(it){
//                    is Resource.Loading ->{
//                        showOfferLoading()
//                    }
//                    is Resource.Success -> {
//                        offerAdapter.differ.submitList(it.data)
//                        hideOfferLoading()
//                    }
//                    is Resource.Error -> {
//                        Snackbar.make(requireView(),it.message.toString(), Snackbar.LENGTH_LONG)
//                            .show()
//                    }
//                    else -> Unit
//                }
//            }
//        }
//
//
//
//        lifecycleScope.launchWhenStarted {
//            viewModel.bestProducts.collectLatest {
//                when(it){
//                    is Resource.Loading ->{
//                        showBestProductsLoading()
//                    }
//                    is Resource.Success -> {
//                        bestProductsAdapter.differ.submitList(it.data)
//                        hideBestProductsLoading()
//                    }
//                    is Resource.Error -> {
//                        Snackbar.make(requireView(),it.message.toString(), Snackbar.LENGTH_LONG)
//                            .show()
//                    }
//                    else -> Unit
//                }
//            }
//        }
//    }
//}

package com.example.navasc.fragments.fragments.categories

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.navasc.data.Category
import com.example.navasc.util.Resource
import com.example.navasc.viewmodel.CategoryViewModel
import com.example.navasc.viewmodel.factory.BaseCategoryViewModelFactory
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.collectLatest

class FrockFragment : BaseCategoryFragment() {

    // Initialize Firestore manually
    private val firestore: FirebaseFirestore by lazy {
        FirebaseFirestore.getInstance()
    }

    // Provide the ViewModel manually using the factory
    val viewModel by viewModels<CategoryViewModel> {
        BaseCategoryViewModelFactory(firestore, Category.Frock)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launchWhenStarted {
            viewModel.offerProducts.collectLatest {
                when (it) {
                    is Resource.Loading -> {
                        showOfferLoading()
                    }
                    is Resource.Success -> {
                        offerAdapter.differ.submitList(it.data)
                        hideOfferLoading()
                    }
                    is Resource.Error -> {
                        Snackbar.make(requireView(), it.message.toString(), Snackbar.LENGTH_LONG).show()
                    }
                    else -> Unit
                }
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.bestProducts.collectLatest {
                when (it) {
                    is Resource.Loading -> {
                        showBestProductsLoading()
                    }
                    is Resource.Success -> {
                        bestProductsAdapter.differ.submitList(it.data)
                        hideBestProductsLoading()
                    }
                    is Resource.Error -> {
                        Snackbar.make(requireView(), it.message.toString(), Snackbar.LENGTH_LONG).show()
                    }
                    else -> Unit
                }
            }
        }
    }
}
