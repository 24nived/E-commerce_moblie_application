package com.example.navasc.fragments.fragments.shoppingActivity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.navasc.R
import com.example.navasc.adapters.HomeViewPagerAdapter
import com.example.navasc.databinding.FragmentHomeBinding
import com.example.navasc.fragments.fragments.categories.BlouseFragment
import com.example.navasc.fragments.fragments.categories.FrockFragment
import com.example.navasc.fragments.fragments.categories.LehangaFragment
import com.example.navasc.fragments.fragments.categories.MainCategoryFragment
import com.example.navasc.fragments.fragments.categories.SareeFragment
import com.example.navasc.fragments.fragments.categories.TopsFragment
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewPager2Adapter: HomeViewPagerAdapter
    private val allCategoriesFragments = arrayListOf<Fragment>(
        MainCategoryFragment(),
        SareeFragment(),
        BlouseFragment(),
        FrockFragment(),
        TopsFragment(),
        LehangaFragment()
    )
    private var filteredCategoriesFragments = ArrayList(allCategoriesFragments)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewPager2Adapter =
            HomeViewPagerAdapter(filteredCategoriesFragments, childFragmentManager, lifecycle)
        binding.viewpagerHome.adapter = viewPager2Adapter

        TabLayoutMediator(binding.tabLayout, binding.viewpagerHome) { tab, position ->
            when (position) {
                0 -> tab.text = "Home"
                1 -> tab.text = "Saree"
                2 -> tab.text = "Blouse"
                3 -> tab.text = "Frock"
                4 -> tab.text = "Tops"
                5 -> tab.text = "Lehanga"
            }
        }.attach()

//        val searchView = view.findViewById<SearchView>(R.id.searchView)
//        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(query: String?): Boolean {
//                return false
//            }
//
//            override fun onQueryTextChange(newText: String?): Boolean {
//                newText?.let { filter(it) }
//                return true
//            }
//        })
//    }
    }
}

//    private fun filter(query: String) {
//        filteredCategoriesFragments.clear()
//        if (query.isEmpty()) {
//            filteredCategoriesFragments.addAll(allCategoriesFragments)
//        } else {
//            val filteredList = allCategoriesFragments.filter { fragment ->
//                when (fragment) {
//                    is MainCategoryFragment -> {
//                        // Replace this with your actual filtering logic for MainCategoryFragment
//                        // Example: fragment.filterItems(query)
//                        // Implement filterItems in MainCategoryFragment to filter its data
//                        true // Placeholder, replace with actual logic
//                    }
//                    is SareeFragment -> {
//                        // Replace this with your actual filtering logic for SareeFragment
//                        true // Placeholder, replace with actual logic
//                    }
//                    is BlouseFragment -> {
//                        // Replace this with your actual filtering logic for BlouseFragment
//                        true // Placeholder, replace with actual logic
//                    }
//                    is FrockFragment -> {
//                        // Replace this with your actual filtering logic for FrockFragment
//                        true // Placeholder, replace with actual logic
//                    }
//                    is TopsFragment -> {
//                        // Replace this with your actual filtering logic for TopsFragment
//                        true // Placeholder, replace with actual logic
//                    }
//                    is LehangaFragment -> {
//                        // Replace this with your actual filtering logic for LehangaFragment
//                        true // Placeholder, replace with actual logic
//                    }
//                    else -> false
//                }
//            }
//            filteredCategoriesFragments.addAll(filteredList)
//        }
//        viewPager2Adapter.notifyDataSetChanged()
//    }
//}
