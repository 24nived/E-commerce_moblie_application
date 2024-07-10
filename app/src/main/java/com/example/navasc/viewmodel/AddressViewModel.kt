//package com.example.navasc.viewmodel
//
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.example.navasc.util.Resource
//import com.google.firebase.auth.FirebaseAuth
//import com.google.firebase.firestore.FirebaseFirestore
//import dagger.hilt.android.lifecycle.HiltViewModel
//import kotlinx.coroutines.flow.MutableSharedFlow
//import kotlinx.coroutines.flow.MutableStateFlow
//import kotlinx.coroutines.flow.asSharedFlow
//import kotlinx.coroutines.flow.asStateFlow
//import kotlinx.coroutines.launch
//import javax.inject.Inject
//
//@HiltViewModel
//class AddressViewModel @Inject constructor(
//    private val firestore: FirebaseFirestore,
//    private val auth: FirebaseAuth
//):ViewModel() {
//
//
//    private val _addNewAddress = MutableStateFlow<Resource<com.example.navasc.data.Address>>(Resource.Unspecified())
//        val addNewAddress = _addNewAddress.asStateFlow()
//
//    private val _error = MutableSharedFlow<String>()
//        val error = _error.asSharedFlow()
//
//
//    fun  addAddress(address: com.example.navasc.data.Address){
//        val validateInputs = validateInputs(address)
//
//        if (validateInputs) {
//            viewModelScope.launch { _addNewAddress.emit(Resource.Loading()) }
//
//
//            firestore.collection("user").document(auth.uid!!).collection("address").document()
//                .set(address).addOnSuccessListener {
//                    viewModelScope.launch { _addNewAddress.emit(Resource.Success(address)) }
//                }.addOnFailureListener {
//                    viewModelScope.launch { _addNewAddress.emit(Resource.Error(it.message.toString())) }
//                }
//        }else{
//            viewModelScope.launch {
//                _error.emit("All fields are required")
//            }
//        }
//    }
//
//    private fun validateInputs(address: com.example.navasc.data.Address): Boolean {
//
//        return address.addressTitle.trim().isNotEmpty() &&
//                address.city.trim().isNotEmpty() &&
//                address.phone.trim().isNotEmpty() &&
//                address.state.trim().isNotEmpty() &&
//                address.fullName.trim().isNotEmpty() &&
//                address.street.trim().isNotEmpty()
//    }
//}

package com.example.navasc.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.navasc.util.Resource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AddressViewModel(
    private val firestore: FirebaseFirestore,
    private val auth: FirebaseAuth
) : ViewModel() {

    private val _addNewAddress = MutableStateFlow<Resource<com.example.navasc.data.Address>>(Resource.Unspecified())
    val addNewAddress = _addNewAddress.asStateFlow()

    private val _error = MutableSharedFlow<String>()
    val error = _error.asSharedFlow()

    fun addAddress(address: com.example.navasc.data.Address) {
        val validateInputs = validateInputs(address)

        if (validateInputs) {
            viewModelScope.launch { _addNewAddress.emit(Resource.Loading()) }

            firestore.collection("user").document(auth.uid!!).collection("address").document()
                .set(address).addOnSuccessListener {
                    viewModelScope.launch { _addNewAddress.emit(Resource.Success(address)) }
                }.addOnFailureListener {
                    viewModelScope.launch { _addNewAddress.emit(Resource.Error(it.message.toString())) }
                }
        } else {
            viewModelScope.launch {
                _error.emit("All fields are required")
            }
        }
    }

    private fun validateInputs(address: com.example.navasc.data.Address): Boolean {
        return address.addressTitle.trim().isNotEmpty() &&
                address.city.trim().isNotEmpty() &&
                address.phone.trim().isNotEmpty() &&
                address.state.trim().isNotEmpty() &&
                address.fullName.trim().isNotEmpty() &&
                address.street.trim().isNotEmpty()
    }
}
