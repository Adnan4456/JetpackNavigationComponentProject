package com.example.jetpacknavigationcomponent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.jetpacknavigationcomponent.databinding.FragmentConfirmDialogBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_confirm_dialog.*


//BottomSheetDialogFragment will on bottom of screen
class ConfirmDialogFragment: BottomSheetDialogFragment() {

    private var _binding: FragmentConfirmDialogBinding? =null
    private val binding get() = _binding!!

    //getting arguments from SendCashFragment.with builtin class by arg extension.
    private  val args: ConfirmDialogFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentConfirmDialogBinding.inflate(layoutInflater,container,false)
        val view = binding.root
        return view
    }

    //Use function to get values because View is created here and ready .
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        val receiverName = args.receiverName
        val amount = args.amount

        tv_message.text = "Do you want to send Rs $amount to $receiverName?"
        binding.btnYes.setOnClickListener{

            Toast.makeText(requireContext(), "$amount has been send to $receiverName",Toast.LENGTH_LONG).show()
            dismiss()
        }
        binding.btnNo.setOnClickListener{
            dismiss()
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}