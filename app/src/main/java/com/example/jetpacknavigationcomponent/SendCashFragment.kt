package com.example.jetpacknavigationcomponent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.jetpacknavigationcomponent.databinding.FragmentSendCashBinding
import kotlinx.android.synthetic.main.fragment_send_cash.*

class SendCashFragment:Fragment() {

    private var _binding:FragmentSendCashBinding? = null

    private val binding get() = _binding!!

    //Args automatically create a class for receiving data from ChooseReceiverFragment(Sender)
    //here we are creating object of that class
    private val args : SendCashFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSendCashBinding.inflate(inflater,container,false)
        val view = binding.root

        //setting default value to edit text from live data
        binding.etAmount.setText(SampleData.defaultAmount.toString())
        //also observe here if this value is changed from setting fragment
        SampleData.defaultAmount.observe(viewLifecycleOwner){
            binding.etAmount.setText(it.toString())
        }

       // we use safe args plugin to receive data.It is type cast.no other type can be received.
        //It give compile time error if type casting error come.
        val receiverName = args.receiverName
        binding.tvReceiver.text = "Send cash to $receiverName"

        binding.btnCancel.setOnClickListener{
            //Go to home fragment on cancel click
            //delete other fragments between them in backstack.
            //popUpTo =  homefragment
            //popupToInclusive = true
            findNavController().popBackStack(R.id.homeFragment, true)
        }

        binding.btnSend.setOnClickListener{

            if(et_amount.text.toString().isEmpty()){
                Toast.makeText(requireContext(),"Please enter cash amount",Toast.LENGTH_LONG).show()
                return@setOnClickListener //if amount is empty then return from here
            }


            val amount = et_amount.text.toString().toLong()
            //No animation will work in this case
            //Because we ae using BottomSheetDialogFragment it has default animation
            val action = SendCashFragmentDirections.actionSendCashFragmentToConfirmDialogFragment(receiverName, amount)
             findNavController().navigate(action)
        }
        binding.btnDone.setOnClickListener{
            val action = SendCashFragmentDirections.actionSendCashFragmentToHomeFragment()
            findNavController().navigate(action)
        }
        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}