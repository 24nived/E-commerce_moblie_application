//package com.example.navasc.viewmodel
//
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.example.navasc.data.Product
//import com.example.navasc.util.Resource
//import com.google.firebase.firestore.FirebaseFirestore
//import dagger.hilt.android.lifecycle.HiltViewModel
//import kotlinx.coroutines.flow.MutableStateFlow
//import kotlinx.coroutines.flow.StateFlow
//import kotlinx.coroutines.launch
//import javax.inject.Inject
//
//
//@HiltViewModel
//class MainCategoryViewModel @Inject constructor(
//    private val firestore: FirebaseFirestore
//):ViewModel() {
//
//
//
//
//    private val _specialProducts = MutableStateFlow<Resource<List<Product>>>(Resource.Unspecified())
//    val specialProducts:StateFlow<Resource<List<Product>>> = _specialProducts
//
//    private val _bestDeals = MutableStateFlow<Resource<List<Product>>>(Resource.Unspecified())
//    val  bestDeals:StateFlow<Resource<List<Product>>> = _bestDeals
//
//    private val _bestProducts = MutableStateFlow<Resource<List<Product>>>(Resource.Unspecified())
//    val  bestProducts:StateFlow<Resource<List<Product>>> = _bestProducts
//
//    init {
//        fetchSpecialProducts()
//        fetchBestDealsProducts()
//        fetchBestProducts()
//    }
//
//    private fun fetchBestProducts() {
//        viewModelScope.launch {
//            _bestProducts.emit(Resource.Loading())
//        }
//
//        firestore.collection("Products").whereEqualTo("category","Bestproducts").limit(10).get()
//
//            .addOnSuccessListener { result ->
//                val bestProductsList = result.toObjects(Product::class.java)
//
//                viewModelScope.launch {
//                    _bestProducts.emit(Resource.Success(bestProductsList))
//                }
//
//            }.addOnFailureListener {
//                viewModelScope.launch {
//                    _bestProducts.emit(Resource.Error(it.message.toString()))
//                }
//            }
//    }
//
//    private fun fetchBestDealsProducts() {
//        viewModelScope.launch {
//            _bestDeals.emit(Resource.Loading())
//        }
//        firestore.collection("Products").whereEqualTo("category","BestDeals").get()
//            .addOnSuccessListener {  bestProd ->
//                val bestDealProductList = bestProd.toObjects(Product::class.java)
//                viewModelScope.launch {
//                    _bestDeals.emit(Resource.Success(bestDealProductList))
//                }
//            }.addOnFailureListener {
//                viewModelScope.launch {
//                    _bestDeals.emit(Resource.Error(it.message.toString()))
//                }
//            }
//    }
//
//    private fun fetchSpecialProducts(){
//        viewModelScope.launch {
//            _specialProducts.emit(Resource.Loading())
//        }
//        firestore.collection("Products").whereEqualTo("category","Special").get()
//            .addOnSuccessListener {  result ->
//                val specialProductList = result.toObjects(Product::class.java)
//                viewModelScope.launch {
//                    _specialProducts.emit(Resource.Success(specialProductList))
//                }
//            }.addOnFailureListener {
//                viewModelScope.launch {
//                    _specialProducts.emit(Resource.Error(it.message.toString()))
//                }
//            }
//    }
//
//    fun searchProducts(query: String) {
//        viewModelScope.launch {
//            // Emit loading state while searching
//            _specialProducts.emit(Resource.Loading())
//        }
//        // Perform a Firestore query to search for products
//        firestore.collection("Products")
//            .whereEqualTo("category", query) // Modify this query as per your search criteria
//            .get()
//            .addOnSuccessListener { result ->
//                // Convert the Firestore result to a list of Product objects
//                val searchResult = result.toObjects(Product::class.java)
//                viewModelScope.launch {
//                    // Emit the search result
//                    _specialProducts.emit(Resource.Success(searchResult))
//                }
//            }.addOnFailureListener { exception ->
//                viewModelScope.launch {
//                    // Emit an error state if the search fails
//                    _specialProducts.emit(Resource.Error(exception.message.toString()))
//                }
//            }
//    }
//}
//
//

package com.example.navasc.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.navasc.data.Product
import com.example.navasc.util.Resource
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainCategoryViewModel(
    private val firestore: FirebaseFirestore
) : ViewModel() {

    private val _specialProducts = MutableStateFlow<Resource<List<Product>>>(Resource.Unspecified())
    val specialProducts: StateFlow<Resource<List<Product>>> = _specialProducts

    private val _bestDeals = MutableStateFlow<Resource<List<Product>>>(Resource.Unspecified())
    val bestDeals: StateFlow<Resource<List<Product>>> = _bestDeals

    private val _bestProducts = MutableStateFlow<Resource<List<Product>>>(Resource.Unspecified())
    val bestProducts: StateFlow<Resource<List<Product>>> = _bestProducts

    init {
        fetchSpecialProducts()
        fetchBestDealsProducts()
        fetchBestProducts()
    }

    private fun fetchBestProducts() {
        viewModelScope.launch {
            _bestProducts.emit(Resource.Loading())
        }

        firestore.collection("Products").whereEqualTo("category", "Bestproducts").limit(10).get()
            .addOnSuccessListener { result ->
                val bestProductsList = result.toObjects(Product::class.java)
                viewModelScope.launch {
                    _bestProducts.emit(Resource.Success(bestProductsList))
                }
            }.addOnFailureListener {
                viewModelScope.launch {
                    _bestProducts.emit(Resource.Error(it.message.toString()))
                }
            }
    }

    private fun fetchBestDealsProducts() {
        viewModelScope.launch {
            _bestDeals.emit(Resource.Loading())
        }
        firestore.collection("Products").whereEqualTo("category", "BestDeals").get()
            .addOnSuccessListener { bestProd ->
                val bestDealProductList = bestProd.toObjects(Product::class.java)
                viewModelScope.launch {
                    _bestDeals.emit(Resource.Success(bestDealProductList))
                }
            }.addOnFailureListener {
                viewModelScope.launch {
                    _bestDeals.emit(Resource.Error(it.message.toString()))
                }
            }
    }

    private fun fetchSpecialProducts() {
        viewModelScope.launch {
            _specialProducts.emit(Resource.Loading())
        }
        firestore.collection("Products").whereEqualTo("category", "Special").get()
            .addOnSuccessListener { result ->
                val specialProductList = result.toObjects(Product::class.java)
                viewModelScope.launch {
                    _specialProducts.emit(Resource.Success(specialProductList))
                }
            }.addOnFailureListener {
                viewModelScope.launch {
                    _specialProducts.emit(Resource.Error(it.message.toString()))
                }
            }
    }

    fun searchProducts(query: String) {
        viewModelScope.launch {
            _specialProducts.emit(Resource.Loading())
        }
        firestore.collection("Products")
            .whereEqualTo("category", query)
            .get()
            .addOnSuccessListener { result ->
                val searchResult = result.toObjects(Product::class.java)
                viewModelScope.launch {
                    _specialProducts.emit(Resource.Success(searchResult))
                }
            }.addOnFailureListener { exception ->
                viewModelScope.launch {
                    _specialProducts.emit(Resource.Error(exception.message.toString()))
                }
            }
    }
}
