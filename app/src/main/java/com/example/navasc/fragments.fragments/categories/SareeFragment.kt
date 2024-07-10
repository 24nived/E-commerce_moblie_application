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
//@AndroidEntryPoint
//class SareeFragment:BaseCategoryFragment() {
//
//    @Inject
//    lateinit var firestore: FirebaseFirestore
//
//    val  viewModel by viewModels<CategoryViewModel> {
//        BaseCategoryViewModelFactory(firestore,Category.Saree)
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
//                        Snackbar.make(requireView(),it.message.toString(),Snackbar.LENGTH_LONG)
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
//                        Snackbar.make(requireView(),it.message.toString(),Snackbar.LENGTH_LONG)
//                            .show()
//                    }
//                    else -> Unit
//                }
//            }
//        }
//
//    }
//
//}

package com.example.navasc.fragments.fragments.categories

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.example.navasc.data.Category
import com.example.navasc.util.Resource
import com.example.navasc.viewmodel.CategoryViewModel
import com.example.navasc.viewmodel.factory.BaseCategoryViewModelFactory
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.collectLatest

class SareeFragment : BaseCategoryFragment() {

    private lateinit var viewModel: CategoryViewModel

    private val firestore: FirebaseFirestore by lazy {
        FirebaseFirestore.getInstance()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       // viewModel = CategoryViewModel(BaseCategoryViewModelFactory(firestore, Category.Saree))

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
                        Snackbar.make(requireView(), it.message.toString(), Snackbar.LENGTH_LONG)
                            .show()
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
                        Snackbar.make(requireView(), it.message.toString(), Snackbar.LENGTH_LONG)
                            .show()
                    }
                    else -> Unit
                }
            }
        }
    }
}
