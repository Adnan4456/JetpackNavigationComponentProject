package com.example.jetpacknavigationcomponent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.jetpacknavigationcomponent.databinding.FragmentSettingsBinding

class SettingsFragment: Fragment() {

    private var _binding: FragmentSettingsBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSettingsBinding.inflate(inflater, container , false)
        val view = binding.root

        //Default value Also set in edit  text from livedata
        binding.etDefaultAmount.setText(SampleData.defaultAmount.value.toString())

        //Change default value from edit text
        binding.btnSaveDefaultAmount.setOnClickListener{
            val amount = binding.etDefaultAmount.text.toString().toLong()
            //assign this value to live data in Sample class and observe it in Send CashFragment.
            //Fragment communication
            SampleData.defaultAmount.value = amount
        }

        binding.btnAbout.setOnClickListener{
            val action = MainNavGraphDirections.actionGlobalAboutFragment()
            findNavController().navigate(action)
        }


        return view

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}