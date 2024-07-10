package com.example.navasc.fragments.fragments.shoppingActivity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.example.navasc.R
import com.example.navasc.databinding.BottomSheetOrderSuccessBinding
import com.example.navasc.databinding.FragmentOrderSuccessBinding

class OrderSuccessFragment : Fragment() {

    private lateinit var binding: FragmentOrderSuccessBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentOrderSuccessBinding.inflate(inflater, container, false)

        showBottomSheet()

        binding.apply {
            backIcon.setOnClickListener { findNavController().navigate(R.id.action_orderSuccessFragment_to_homeFragment) }

            backHomeButton.setOnClickListener { findNavController().navigate(R.id.action_orderSuccessFragment_to_homeFragment) }
        }

        return binding.root
    }

    private fun showBottomSheet() {
        val bottomSheet = BottomSheetDialog(requireContext())
        val bottomSheetBinding = BottomSheetOrderSuccessBinding.inflate(LayoutInflater.from(requireContext()))

        bottomSheet.setContentView(bottomSheetBinding.root)

        bottomSheetBinding.backHomeButton.setOnClickListener {
            findNavController().navigate(R.id.action_orderSuccessFragment_to_homeFragment)
            bottomSheet.dismiss()
        }

        bottomSheet.show()
    }
}