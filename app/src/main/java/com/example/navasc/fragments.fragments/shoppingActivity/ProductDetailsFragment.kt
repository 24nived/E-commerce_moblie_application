package com.example.navasc.fragments.fragments.shoppingActivity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.navasc.R
import com.example.navasc.adapters.ColorsAdapter
import com.example.navasc.adapters.ProductAdapter
import com.example.navasc.adapters.SizeAdapter
import com.example.navasc.adapters.ViewPager2Images
import com.example.navasc.data.CartProduct
import com.example.navasc.databinding.FragmentProductDetailsBinding
import com.example.navasc.util.Resource
import com.example.navasc.util.hideBottomNavigationView
import com.example.navasc.viewmodel.DetailsViewModel
//import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

//@AndroidEntryPoint
//class ProductDetailsFragment: Fragment() {
//    private val args by navArgs<ProductDetailsFragmentArgs>()
//    private lateinit var binding: FragmentProductDetailsBinding
//    private val viewPagerAdapter by lazy { ViewPager2Images()}
//    private val sizeAdapter by lazy { SizeAdapter() }
//    private val colorsAdapter by lazy { ColorsAdapter() }
//    private var selectedColor:Int? = null
//    private var selectedSize:String? = null
//    private val viewModel by viewModels<DetailsViewModel>()
//    private lateinit var recyclerView: RecyclerView
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//
//
////        val searchView = findViewById<SearchView>(R.id.searchView)
////        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
////            override fun onQueryTextSubmit(query: String): Boolean {
////                return false
////            }
////
////            override fun onQueryTextChange(newText: String): Boolean {
////                val adapter = recyclerView.adapter
////                if (adapter is ProductAdapter) {
////
////                    adapter.filter(newText)
////                }
////                return true
////            }
////        })
//
//        hideBottomNavigationView()
//        binding = FragmentProductDetailsBinding.inflate(inflater,container,false)
//        return binding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        val product = args.product
//
//        setupSizeRecView()
//        setupColorsRecView()
//        setupViewPager()
//
//        binding.imageClose.setOnClickListener {
//            findNavController().navigateUp()
//        }
//
//        sizeAdapter.onItemClick= {
//            selectedSize = it
//        }
//
//        colorsAdapter.onItemClick = {
//            selectedColor = it
//        }
//
//        binding.addToCart.setOnClickListener {
//            viewModel.addUpdateProductToCart(
//                CartProduct( product,1,selectedColor,selectedSize))
//        }
//        lifecycleScope.launchWhenStarted {
//            viewModel.addToCart.collectLatest {
//                when(it){
//                    is Resource.Loading ->{
//                        binding.addToCart.startAnimation()
//                    }
//                    is Resource.Success ->{
//                        binding.addToCart.revertAnimation()
//                        binding.addToCart.setBackgroundColor(resources.getColor(R.color.black))
//                    }
//                    is Resource.Error ->{
//                        binding.addToCart.stopAnimation()
//                        Toast.makeText(requireContext(),it.message,Toast.LENGTH_SHORT).show()
//                    }
//                    else -> Unit
//                }
//            }
//        }
//
//        binding.apply {
//            tvProductName.text = product.name
//            tvProductPrice.text ="₹ ${product.price}"
//            tvProductDescription.text = product.description
//
//            if (product.colors.isNullOrEmpty()){
//                tvProductColor.visibility = View.INVISIBLE
//            }
//            if (product.sizes.isNullOrEmpty()){
//                tvProductSize.visibility = View.INVISIBLE
//            }
//        }
//
//        //Updating adapters
//
//        viewPagerAdapter.differ.submitList(product.images)
//        product.colors?.let {
//            colorsAdapter.differ.submitList(it)
//        }
//        product.sizes?.let {
//            sizeAdapter.differ.submitList(it)
//        }
//
//    }
//
//    private fun setupViewPager() {
//        binding.apply {
//            viewPagerProductImages.adapter = viewPagerAdapter
//        }
//    }
//
//    private fun setupColorsRecView() {
//       binding.recColors.apply {
//           adapter = colorsAdapter
//           layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
//       }
//    }
//
//    private fun setupSizeRecView() {
//        binding.recSize.apply {
//            adapter = sizeAdapter
//            layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
//        }
//    }
//
//}
class ProductDetailsFragment : Fragment() {
    private val args by navArgs<ProductDetailsFragmentArgs>()
    private lateinit var binding: FragmentProductDetailsBinding
    private val viewPagerAdapter by lazy { ViewPager2Images() }
    private val sizeAdapter by lazy { SizeAdapter() }
    private val colorsAdapter by lazy { ColorsAdapter() }
    private var selectedColor: Int? = null
    private var selectedSize: String? = null
    private lateinit var viewModel: DetailsViewModel
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        hideBottomNavigationView()
        binding = FragmentProductDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Create the ViewModel manually
        viewModel = ViewModelProvider(this).get(DetailsViewModel::class.java)

        val product = args.product

        setupSizeRecView()
        setupColorsRecView()
        setupViewPager()

        binding.imageClose.setOnClickListener {
            findNavController().navigateUp()
        }

        sizeAdapter.onItemClick = {
            selectedSize = it
        }

        colorsAdapter.onItemClick = {
            selectedColor = it
        }

        binding.addToCart.setOnClickListener {
            viewModel.addUpdateProductToCart(
                CartProduct(product, 1, selectedColor, selectedSize)
            )
        }

        lifecycleScope.launchWhenStarted {
            viewModel.addToCart.collectLatest {
                when (it) {
                    is Resource.Loading -> {
                        binding.addToCart.startAnimation()
                    }
                    is Resource.Success -> {
                        binding.addToCart.revertAnimation()
                        binding.addToCart.setBackgroundColor(resources.getColor(R.color.black))
                    }
                    is Resource.Error -> {
                        binding.addToCart.stopAnimation()
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                    }
                    else -> Unit
                }
            }
        }

        binding.apply {
            tvProductName.text = product.name
            tvProductPrice.text = "₹ ${product.price}"
            tvProductDescription.text = product.description

            if (product.colors.isNullOrEmpty()) {
                tvProductColor.visibility = View.INVISIBLE
            }
            if (product.sizes.isNullOrEmpty()) {
                tvProductSize.visibility = View.INVISIBLE
            }
        }

        //Updating adapters

        viewPagerAdapter.differ.submitList(product.images)
        product.colors?.let {
            colorsAdapter.differ.submitList(it)
        }
        product.sizes?.let {
            sizeAdapter.differ.submitList(it)
        }
    }

    private fun setupViewPager() {
        binding.viewPagerProductImages.adapter = viewPagerAdapter
    }

    private fun setupColorsRecView() {
        binding.recColors.apply {
            adapter = colorsAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }
    }

    private fun setupSizeRecView() {
        binding.recSize.apply {
            adapter = sizeAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }
    }
}
