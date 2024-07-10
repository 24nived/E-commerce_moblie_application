package com.example.navasc.viewmodel
//
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.example.navasc.data.order.Order
//import com.example.navasc.util.Resource
//import com.google.firebase.auth.FirebaseAuth
//import com.google.firebase.firestore.FirebaseFirestore
//import dagger.hilt.android.lifecycle.HiltViewModel
//import kotlinx.coroutines.flow.MutableStateFlow
//import kotlinx.coroutines.flow.asStateFlow
//import kotlinx.coroutines.launch
//import javax.inject.Inject
//
//@HiltViewModel
//class AllOrdersViewModel @Inject constructor(
//    private val firestore: FirebaseFirestore,
//    private val auth: FirebaseAuth
//):ViewModel() {
//
//    private val _allOrders = MutableStateFlow<Resource<List<Order>>>(Resource.Unspecified())
//        val allOrders = _allOrders.asStateFlow()
//
//    init {
//        getAllOrders()
//    }
//
//    fun getAllOrders(){
//        viewModelScope.launch {
//            _allOrders.emit(Resource.Loading())
//        }
//        firestore.collection("user").document(auth.uid!!).collection("orders").get()
//            .addOnSuccessListener {
//                val orders = it.toObjects(Order::class.java)
//                viewModelScope.launch {
//                    _allOrders.emit(Resource.Success(orders))
//                }
//            }.addOnFailureListener {
//                viewModelScope.launch {
//                    _allOrders.emit(Resource.Error(it.message.toString()))
//                }
//            }
//    }
//}
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.Toast
//import androidx.fragment.app.Fragment
//import androidx.fragment.app.viewModels
//import androidx.lifecycle.lifecycleScope
//import androidx.navigation.fragment.findNavController
//import androidx.recyclerview.widget.LinearLayoutManager
//import androidx.recyclerview.widget.RecyclerView
//import com.example.navasc.adapters.AllOrdersAdapter
//import com.example.navasc.databinding.FragmentOrdersBinding
//import com.example.navasc.util.Resource
//import com.example.navasc.viewmodel.AllOrdersViewModel
//import kotlinx.coroutines.flow.collectLatest
//
//class AllOrdersViewModel : Fragment() {
//    private lateinit var binding: FragmentOrdersBinding
//    private val viewModel: AllOrdersViewModel by viewModels()
//    private val allOrdersAdapter by lazy { AllOrdersAdapter() }
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        binding = FragmentOrdersBinding.inflate(inflater, container, false)
//        return binding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        setupOrderRecView()
//
//        lifecycleScope.launchWhenStarted {
//            viewModel.allOrders.collectLatest {
//                when (it) {
//                    is Resource.Loading -> {
//                        binding.progressbarAllOrders.visibility = View.VISIBLE
//                    }
//                    is Resource.Success -> {
//                        binding.progressbarAllOrders.visibility = View.GONE
//                        allOrdersAdapter.differ.submitList(it.data)
//                        if (it.data.isNullOrEmpty()) {
//                            binding.tvEmptyOrders.visibility = View.VISIBLE
//                        }
//                    }
//                    is Resource.Error -> {
//                        binding.progressbarAllOrders.visibility = View.GONE
//                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
//                    }
//                    else -> Unit
//                }
//            }
//        }
//
//        allOrdersAdapter.onClick = {
//            val action = AllOrdersFragmentDirections.actionAllOrdersFragmentToOrderDetailFragment(it)
//            findNavController().navigate(action)
//        }
//    }
//
//    private fun setupOrderRecView() {
//        binding.rvAllOrders.apply {
//            adapter = allOrdersAdapter
//            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
//        }
//    }
//}
