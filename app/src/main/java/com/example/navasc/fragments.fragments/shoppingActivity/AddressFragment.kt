//package com.example.navasc.fragments.fragments.shoppingActivity
//
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.Toast
//import androidx.fragment.app.Fragment
//import androidx.fragment.app.viewModels
//import androidx.lifecycle.lifecycleScope
//import androidx.navigation.fragment.findNavController
//import com.example.navasc.R
//import com.example.navasc.data.Address
//import com.example.navasc.databinding.FragmentAddressBinding
//import com.example.navasc.util.Resource
//import com.example.navasc.viewmodel.AddressViewModel
//import dagger.hilt.android.AndroidEntryPoint
//import kotlinx.coroutines.flow.collectLatest
//
//@AndroidEntryPoint
//class AddressFragment: Fragment() {
//    private lateinit var binding:FragmentAddressBinding
//    val viewModel by viewModels<AddressViewModel> ()
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        lifecycleScope.launchWhenStarted {
//
//            viewModel.addNewAddress.collectLatest {
//                when(it){
//                    is Resource.Loading ->{
//                        binding.progressbarAddress.visibility = View.VISIBLE
//                    }
//                    is Resource.Success ->{
//                        binding.progressbarAddress.visibility = View.INVISIBLE
//                        findNavController().navigateUp()
//                    }
//                    is Resource.Error ->{
//                        Toast.makeText(requireContext(),it.message.toString(),Toast.LENGTH_SHORT).show()
//                    }
//                    else ->Unit
//                }
//            }
//        }
//
//        lifecycleScope.launchWhenStarted {
//            viewModel.error.collectLatest {
//                Toast.makeText(requireContext(),it.toString(),Toast.LENGTH_SHORT).show()
//            }
//        }
//    }
//
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        binding = FragmentAddressBinding.inflate(inflater,container,false)
//        return binding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//
//
//        binding.apply {
//            buttonSave.setOnClickListener {
//
//                val addressTitle = edAddressTitle.text.toString()
//                val fullName = edFullName.text.toString()
//                val phone = edPhone.text.toString()
//                val city = edCity.text.toString()
//                val street = edStreet.text.toString()
//                val state = edState.text.toString()
//
//                val address = Address(addressTitle, fullName, street, phone, city, state)
//
//                viewModel.addAddress(address)
//            }
//        }
//    }
//
//}
package com.example.navasc.fragments.fragments.shoppingActivity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.navasc.R
import com.example.navasc.data.Address
import com.example.navasc.databinding.FragmentAddressBinding
import com.example.navasc.util.Resource
import com.example.navasc.viewmodel.AddressViewModel
import kotlinx.coroutines.flow.collectLatest

class AddressFragment : Fragment() {
    private lateinit var binding: FragmentAddressBinding
    private lateinit var viewModel: AddressViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddressBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(AddressViewModel::class.java)

        lifecycleScope.launchWhenStarted {
            viewModel.addNewAddress.collectLatest {
                when (it) {
                    is Resource.Loading -> {
                        binding.progressbarAddress.visibility = View.VISIBLE
                    }
                    is Resource.Success -> {
                        binding.progressbarAddress.visibility = View.INVISIBLE
                        findNavController().navigateUp()
                    }
                    is Resource.Error -> {
                        Toast.makeText(requireContext(), it.message.toString(), Toast.LENGTH_SHORT).show()
                    }
                    else -> Unit
                }
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.error.collectLatest {
                Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_SHORT).show()
            }
        }

        binding.apply {
            buttonSave.setOnClickListener {
                val addressTitle = edAddressTitle.text.toString()
                val fullName = edFullName.text.toString()
                val phone = edPhone.text.toString()
                val city = edCity.text.toString()
                val street = edStreet.text.toString()
                val state = edState.text.toString()

                val address = Address(addressTitle, fullName, street, phone, city, state)

                viewModel.addAddress(address)
            }
        }
    }
}
