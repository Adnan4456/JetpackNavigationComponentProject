package com.example.jetpacknavigationcomponent

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavOptions
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController

import com.example.jetpacknavigationcomponent.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding:FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.to avoid Memory leak
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeBinding.inflate(inflater, container , false)
        val view = binding.root


        binding.btnSendMoney.setOnClickListener{

            val action = HomeFragmentDirections.actionHomeFragmentToChooseReceiverFragment()
            findNavController().navigate(action)
        }
        binding.btnViewBalance.setOnClickListener{
//            val action = HomeFragmentDirections.actionHomeFragmentToViewBalanceFragment()
            //Navigating to next fragment using ID of the fragment and setting animation in statically.
            val navOption = NavOptions.Builder()
                .setEnterAnim(R.anim.slide_in_right)
                .setExitAnim(R.anim.slide_out_left)
                .setPopEnterAnim(R.anim.slide_in_left)
                .setPopExitAnim(R.anim.slide_out_right)
                .build()
            findNavController().navigate(R.id.viewBalanceFragment,null , navOption)
        }
        binding.btnTransctions.setOnClickListener{
            //Navigating to next fragment using navDirection object
            val action = HomeFragmentDirections.actionHomeFragmentToViewTranscationsFragment()
            findNavController().navigate(action)
        }

        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}