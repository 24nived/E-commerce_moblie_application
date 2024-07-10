//package com.example.navasc.activities
//
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import androidx.activity.viewModels
//import androidx.lifecycle.lifecycleScope
//import androidx.navigation.NavController
//
//import androidx.navigation.fragment.NavHostFragment
//import androidx.navigation.ui.setupWithNavController
//
//import com.example.navasc.R
//import com.example.navasc.databinding.ActivityShoppingBinding
//import com.example.navasc.util.Resource
//import com.example.navasc.viewmodel.CartViewModel
//import com.google.android.material.bottomnavigation.BottomNavigationView
//import dagger.hilt.android.AndroidEntryPoint
//import kotlinx.coroutines.flow.collectLatest
//
//
//@AndroidEntryPoint
//class ShoppingActivity : AppCompatActivity() {
//
//    private lateinit var binding: ActivityShoppingBinding
//    private lateinit var navController: NavController
//
//    val viewModel by viewModels<CartViewModel>()
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityShoppingBinding.inflate(layoutInflater)
//        val view = binding.root
//        setContentView(view)
//        val navHFragment = supportFragmentManager.findFragmentById(R.id.shopping_nav_host) as NavHostFragment
//        navController = navHFragment.navController
//        onNavigationCont()
//
//        lifecycleScope.launchWhenStarted {
//            viewModel.cartProducts.collectLatest {
//                when(it){
//                    is Resource.Success ->{
//                        val count = it.data?.size ?:0
//                        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
//                        bottomNavigation.getOrCreateBadge(R.id.cartFragment).apply {
//                            number = count
//                            backgroundColor = resources.getColor(R.color.g_black)
//                        }
//                    }else ->Unit
//                }
//            }
//        }
//        }
//    private fun onNavigationCont() {
//        binding.bottomNavigation.setupWithNavController(navController)
//    }
//
//
//}

package com.example.navasc.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.navasc.R
import com.example.navasc.databinding.ActivityShoppingBinding
import com.example.navasc.util.Resource
import com.example.navasc.viewmodel.CartViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.flow.collectLatest

class ShoppingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityShoppingBinding
    private lateinit var navController: NavController

    private lateinit var viewModel: CartViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShoppingBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.shopping_nav_host) as NavHostFragment
        navController = navHostFragment.navController
        onNavigationCont()

        // Initialize the ViewModel
        viewModel = ViewModelProvider(this).get(CartViewModel::class.java)

        lifecycleScope.launchWhenStarted {
            viewModel.cartProducts.collectLatest {
                when(it){
                    is Resource.Success -> {
                        val count = it.data?.size ?: 0
                        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
                        bottomNavigation.getOrCreateBadge(R.id.cartFragment).apply {
                            number = count
                            backgroundColor = resources.getColor(R.color.g_black)
                        }
                    }
                    else -> Unit
                }
            }
        }
    }

    private fun onNavigationCont() {
        binding.bottomNavigation.setupWithNavController(navController)
    }
}
