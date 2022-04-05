package com.example.jetpacknavigationcomponent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.jetpacknavigationcomponent.databinding.FragmentViewBalanceBinding

class ViewBalanceFragment:Fragment() {

    private var _binding:FragmentViewBalanceBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.to avoid Memory leak
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentViewBalanceBinding.inflate(inflater ,container , false)
        val view = binding.root

        return view
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}