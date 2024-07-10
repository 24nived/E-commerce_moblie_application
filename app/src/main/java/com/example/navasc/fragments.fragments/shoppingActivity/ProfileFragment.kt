package com.example.navasc.fragments.fragments.shoppingActivity
//
//
//import android.content.Intent
//import android.graphics.Color
//import android.graphics.drawable.ColorDrawable
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.Toast
//import androidx.fragment.app.Fragment
//import androidx.fragment.app.viewModels
//import androidx.lifecycle.lifecycleScope
//import androidx.navigation.fragment.findNavController
//import com.bumptech.glide.Glide
//import com.example.navasc.R
//import com.example.navasc.activities.LoginRegisterActivity
//import com.example.navasc.databinding.FragmentProfileBinding
//import com.example.navasc.util.Resource
//import com.example.navasc.util.showBottomNavigationView
//import com.example.navasc.viewmodel.ProfileViewModel
//import dagger.hilt.android.AndroidEntryPoint
//import kotlinx.coroutines.flow.collectLatest
//
//@AndroidEntryPoint
//class ProfileFragment : Fragment(R.layout.fragment_profile) {
//    private lateinit var binding: FragmentProfileBinding
//    private val viewModel by viewModels<ProfileViewModel>()
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        binding = FragmentProfileBinding.inflate(inflater,container,false)
//        return binding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        binding.constraintProfile.setOnClickListener {
//            findNavController().navigate(R.id.action_profileFragment_to_userAccountFragment)
//        }
//        binding.linearAllOrders.setOnClickListener {
//            findNavController().navigate(R.id.action_profileFragment_to_allOrdersFragment)
//        }
////     binding.linearBilling.setOnClickListener {
////         val action = ProfileFragmentDirections.actionProfileFragmentToAddressFragment(0f,
////             emptyArray()
////         )
////         findNavController().navigate(action)
////     }
//
//        binding.linearLogOut.setOnClickListener {
//            viewModel.logOut()
//            val intent = Intent(requireActivity(),LoginRegisterActivity::class.java)
//            startActivity(intent)
//            requireActivity().finish()
//        }
//
//        binding.tvVersion.text = "Version ${BuildConfig.VERSION_CODE}"
//
//        lifecycleScope.launchWhenStarted {
//            viewModel.user.collectLatest {
//                when(it){
//                    is Resource.Loading ->{
//                       binding.progressbarSettings.visibility = View.VISIBLE
//                    }
//                    is Resource.Success ->{
//                   binding.progressbarSettings.visibility = View.GONE
//                        Glide.with(requireView()).load(it.data!!.imagePath)
//                            .error(ColorDrawable(Color.BLACK))
//                            .into(binding.imageUser)
//                        binding.tvUserName.text = "${it.data.firstName} ${it.data.lastName}"
//                    }
//                    is Resource.Error ->{
//                       binding.progressbarSettings.visibility = View.GONE
//                        Toast.makeText(requireContext(),it.message, Toast.LENGTH_SHORT).show()
//                    }else -> Unit
//                }
//            }
//        }
//    }
//
//    override fun onResume() {
//        super.onResume()
//
//        showBottomNavigationView()
//    }
//
//
//}
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.navasc.R
import com.example.navasc.activities.LoginRegisterActivity
import com.example.navasc.databinding.FragmentProfileBinding
import com.example.navasc.util.Resource
import com.example.navasc.util.showBottomNavigationView
import com.example.navasc.viewmodel.ProfileViewModel
import kotlinx.coroutines.flow.collectLatest

class ProfileFragment : Fragment(R.layout.fragment_profile) {
    private lateinit var binding: FragmentProfileBinding
    private val viewModel: ProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.constraintProfile.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_userAccountFragment)
        }
        binding.linearAllOrders.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_allOrdersFragment)
        }

        binding.linearLogOut.setOnClickListener {
            viewModel.logOut()
            val intent = Intent(requireActivity(),LoginRegisterActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }

        // Replace BuildConfig.VERSION_CODE with a hard-coded version code or another value as needed
        val versionCode = 1 // Example: use 1 as the version code
        binding.tvVersion.text = "Version $versionCode"

        lifecycleScope.launchWhenStarted {
            viewModel.user.collectLatest {
                when(it){
                    is Resource.Loading ->{
                        binding.progressbarSettings.visibility = View.VISIBLE
                    }
                    is Resource.Success ->{
                        binding.progressbarSettings.visibility = View.GONE
                        Glide.with(requireView()).load(it.data!!.imagePath)
                            .error(ColorDrawable(Color.BLACK))
                            .into(binding.imageUser)
                        binding.tvUserName.text = "${it.data.firstName} ${it.data.lastName}"
                    }
                    is Resource.Error ->{
                        binding.progressbarSettings.visibility = View.GONE
                        Toast.makeText(requireContext(),it.message, Toast.LENGTH_SHORT).show()
                    }else -> Unit
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()

        showBottomNavigationView()
    }
}
