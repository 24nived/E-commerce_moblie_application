//package com.example.navasc.fragments.fragments.shoppingActivity
//
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.Toast
//import androidx.appcompat.app.AlertDialog
//import androidx.fragment.app.Fragment
//import androidx.fragment.app.viewModels
//import androidx.lifecycle.lifecycleScope
//import androidx.navigation.fragment.findNavController
//import androidx.navigation.fragment.navArgs
//import androidx.recyclerview.widget.LinearLayoutManager
//import androidx.recyclerview.widget.RecyclerView
//import com.example.navasc.R
//import com.example.navasc.adapters.AddressAdapter
//import com.example.navasc.adapters.BillingProductsAdapter
//import com.example.navasc.data.Address
//import com.example.navasc.data.CartProduct
//import com.example.navasc.data.order.Order
//import com.example.navasc.data.order.OrderStatus
//import com.example.navasc.databinding.FragmentBillingBinding
//import com.example.navasc.util.HorizontalItemDecoration
//import com.example.navasc.util.Resource
//import com.example.navasc.viewmodel.BillingViewModel
//import com.example.navasc.viewmodel.OrderViewModel
//import com.google.android.material.snackbar.Snackbar
//import dagger.hilt.android.AndroidEntryPoint
//import kotlinx.coroutines.flow.collectLatest
//
//
//@AndroidEntryPoint
//class BillingFragment: Fragment() {
//
//    private lateinit var  binding:FragmentBillingBinding
//    private val addressAdapter by lazy { AddressAdapter() }
//    private val billingProductsAdapter by lazy { BillingProductsAdapter() }
//    private val billingViewModel by viewModels<BillingViewModel>()
//    private val args by navArgs<BillingFragmentArgs>()
//    private var products = emptyList<CartProduct>()
//    private var totalPrice = 0f
//    private var selectedAddress:Address?= null
//    private val  orderViewModel by viewModels<OrderViewModel> ()
//
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        products = args.products.toList()
//        totalPrice = args.totalPrice
//    }
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        binding = FragmentBillingBinding.inflate(inflater,container,false)
//        return binding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        setupBillingProductsRv()
//        setupAddressRv()
//
//        binding.imageAddAddress.setOnClickListener {
//            findNavController().navigate(R.id.action_billingFragment_to_addressFragment)
//        }
//
//
//        lifecycleScope.launchWhenStarted {
//
//        billingViewModel.address.collectLatest {
//            when(it){
//
//                is Resource.Loading -> {
//                    binding.progressbarAddress.visibility = View.VISIBLE
//                }
//                is  Resource.Success ->{
//                    addressAdapter.differ.submitList(it.data)
//                    binding.progressbarAddress.visibility = View.GONE
//                }
//                is Resource.Error ->{
//                    binding.progressbarAddress.visibility = View.GONE
//                    Toast.makeText(requireContext(),"Error ${it.message}",Toast.LENGTH_SHORT).show()
//                }
//                else -> Unit
//                }
//            }
//        }
//
//        lifecycleScope.launchWhenStarted {
//            orderViewModel.order.collectLatest {
//                when(it){
//
//                    is Resource.Loading -> {
//                        binding.buttonPlaceOrder.startAnimation()
//                    }
//                    is  Resource.Success ->{
//                        binding.buttonPlaceOrder.revertAnimation()
//                        findNavController().navigate(R.id.action_billingFragment_to_orderSuccessFragment)
//                       // Snackbar.make(requireView(),"Your order is placed ",Snackbar.LENGTH_LONG).show()
//
//                    }
//                    is Resource.Error ->{
//                        binding.buttonPlaceOrder.revertAnimation()
//                        Toast.makeText(requireContext(),"Error ${it.message}",Toast.LENGTH_SHORT).show()
//                    }
//                    else -> Unit
//                }
//            }
//        }
//
//        billingProductsAdapter.differ.submitList(products)
//        binding.tvTotalPrice.text = "₹ $totalPrice"
//
//        addressAdapter.onClick = {
//            selectedAddress = it
//        }
//        binding.buttonPlaceOrder.setOnClickListener {
//            if (selectedAddress == null){
//                Toast.makeText(requireContext(),"Please select an address",Toast.LENGTH_SHORT).show()
//                return@setOnClickListener
//            }
//            else{
//                showOrderConfirmationDialog()
//            }
//        }
//    }
//
//    private fun showOrderConfirmationDialog() {
//
//
//                val alertDialog = AlertDialog.Builder(requireContext()).apply {
//                    setTitle("Order items")
//                    setMessage("Do you want to order your cart items?")
//                    setNegativeButton("Cancel"){dialog,_ ->
//                        dialog.dismiss()
//                    }
//                    setPositiveButton("Yes"){dialog,_ ->
//                        val order = Order(
//                            OrderStatus.Ordered.status,
//                            totalPrice,
//                            products,
//                            selectedAddress!!
//                        )
//                        orderViewModel.placeOrder(order)
//                        dialog.dismiss()
//                    }
//                }
//                alertDialog.create()
//                alertDialog.show()
//            }
//
//
//
//    private fun setupAddressRv() {
//       binding.rvAddress.apply {
//           layoutManager = LinearLayoutManager(requireContext(),RecyclerView.HORIZONTAL,false)
//           adapter = addressAdapter
//           addItemDecoration(HorizontalItemDecoration())
//       }
//    }
//
//    private fun setupBillingProductsRv() {
//      binding.rvProducts.apply {
//          layoutManager = LinearLayoutManager(requireContext(),RecyclerView.HORIZONTAL,false)
//          adapter = billingProductsAdapter
//          addItemDecoration(HorizontalItemDecoration())
//      }
//    }
//
//}

package com.example.navasc.fragments.fragments.shoppingActivity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.navasc.R
import com.example.navasc.adapters.AddressAdapter
import com.example.navasc.adapters.BillingProductsAdapter
import com.example.navasc.data.Address
import com.example.navasc.data.CartProduct
import com.example.navasc.data.order.Order
import com.example.navasc.data.order.OrderStatus
import com.example.navasc.databinding.FragmentBillingBinding
import com.example.navasc.util.HorizontalItemDecoration
import com.example.navasc.util.Resource
import com.example.navasc.viewmodel.BillingViewModel
import com.example.navasc.viewmodel.OrderViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.collectLatest

class BillingFragment : Fragment() {
    private lateinit var binding: FragmentBillingBinding
    private val addressAdapter by lazy { AddressAdapter() }
    private val billingProductsAdapter by lazy { BillingProductsAdapter() }
    private val billingViewModel: BillingViewModel by viewModels()
    private val args by navArgs<BillingFragmentArgs>()
    private var products = emptyList<CartProduct>()
    private var totalPrice = 0f
    private var selectedAddress: Address? = null
    private val orderViewModel: OrderViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        products = args.products.toList()
        totalPrice = args.totalPrice
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBillingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupBillingProductsRv()
        setupAddressRv()

        binding.imageAddAddress.setOnClickListener {
            findNavController().navigate(R.id.action_billingFragment_to_addressFragment)
        }

        lifecycleScope.launchWhenStarted {
            billingViewModel.address.collectLatest {
                when (it) {
                    is Resource.Loading -> {
                        binding.progressbarAddress.visibility = View.VISIBLE
                    }
                    is Resource.Success -> {
                        addressAdapter.differ.submitList(it.data)
                        binding.progressbarAddress.visibility = View.GONE
                    }
                    is Resource.Error -> {
                        binding.progressbarAddress.visibility = View.GONE
                        Toast.makeText(requireContext(), "Error ${it.message}", Toast.LENGTH_SHORT).show()
                    }
                    else -> Unit
                }
            }
        }

        lifecycleScope.launchWhenStarted {
            orderViewModel.order.collectLatest {
                when (it) {
                    is Resource.Loading -> {
                        binding.buttonPlaceOrder.startAnimation()
                    }
                    is Resource.Success -> {
                        binding.buttonPlaceOrder.revertAnimation()
                        findNavController().navigate(R.id.action_billingFragment_to_orderSuccessFragment)
                    }
                    is Resource.Error -> {
                        binding.buttonPlaceOrder.revertAnimation()
                        Toast.makeText(requireContext(), "Error ${it.message}", Toast.LENGTH_SHORT).show()
                    }
                    else -> Unit
                }
            }
        }

        billingProductsAdapter.differ.submitList(products)
        binding.tvTotalPrice.text = "₹ $totalPrice"

        addressAdapter.onClick = {
            selectedAddress = it
        }

        binding.buttonPlaceOrder.setOnClickListener {
            if (selectedAddress == null) {
                Toast.makeText(requireContext(), "Please select an address", Toast.LENGTH_SHORT).show()
            } else {
                showOrderConfirmationDialog()
            }
        }
    }

    private fun showOrderConfirmationDialog() {
        val alertDialog = AlertDialog.Builder(requireContext()).apply {
            setTitle("Order items")
            setMessage("Do you want to order your cart items?")
            setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            setPositiveButton("Yes") { dialog, _ ->
                val order = Order(
                    OrderStatus.Ordered.status,
                    totalPrice,
                    products,
                    selectedAddress!!
                )
                orderViewModel.placeOrder(order)
                dialog.dismiss()
            }
        }.create()
        alertDialog.show()
    }

    private fun setupAddressRv() {
        binding.rvAddress.apply {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
            adapter = addressAdapter
            addItemDecoration(HorizontalItemDecoration())
        }
    }

    private fun setupBillingProductsRv() {
        binding.rvProducts.apply {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
            adapter = billingProductsAdapter
            addItemDecoration(HorizontalItemDecoration())
        }
    }
}
