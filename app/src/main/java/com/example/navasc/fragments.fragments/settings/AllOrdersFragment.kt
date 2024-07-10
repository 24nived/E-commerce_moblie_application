package com.example.navasc.fragments.fragments.settings
//
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.navasc.adapters.AllOrdersAdapter
import com.example.navasc.databinding.FragmentOrdersBinding
import com.example.navasc.util.Resource
//import com.example.navasc.viewmodel.AllOrdersViewModel
//import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
//
//
//@AndroidEntryPoint
//class AllOrdersFragment : Fragment(){
//    private lateinit var binding:FragmentOrdersBinding
//    private val viewModel by viewModels<AllOrdersViewModel>()
//    private val allOrdersAdapter by lazy { AllOrdersAdapter() }
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        binding = FragmentOrdersBinding.inflate(inflater,container,false)
//        return binding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        setupOrderRecView()
//
//        lifecycleScope.launchWhenStarted {
//            viewModel.allOrders.collectLatest{
//                when(it){
//                    is Resource.Loading ->{
//                        binding.progressbarAllOrders.visibility = View.VISIBLE
//                    }
//                    is Resource.Success ->{
//                        binding.progressbarAllOrders.visibility = View.GONE
//                       allOrdersAdapter.differ.submitList(it.data)
//                        if (it.data.isNullOrEmpty()){
//                            binding.tvEmptyOrders.visibility = View.VISIBLE
//                        }
//                    }
//                    is Resource.Error ->{
//                        binding.progressbarAllOrders.visibility = View.GONE
//                        Toast.makeText(requireContext(),it.message, Toast.LENGTH_SHORT).show()
//                    }else -> Unit
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
//            layoutManager = LinearLayoutManager(requireContext(),RecyclerView.VERTICAL,false)
//        }
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

//class AllOrdersFragment : Fragment() {
//    private lateinit var binding:FragmentOrdersBinding
//    private val viewModel: AllOrdersViewModel by viewModels<AllOrdersViewModel>()
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
