package com.example.jetpacknavigationcomponent

import android.app.NotificationManager
import android.app.PendingIntent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.jetpacknavigationcomponent.databinding.FragmentChooseReceiverBinding
import kotlinx.android.synthetic.main.fragment_choose_receiver.*
import kotlinx.android.synthetic.main.fragment_send_cash.*

class ChooseReceiverFragment: Fragment() {

    private var _binding: FragmentChooseReceiverBinding? =null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentChooseReceiverBinding.inflate(inflater,container,false)
        val view = binding.root




        binding.btnCancel.setOnClickListener{
            //it will close this fragment from backstack and previous fragment will auyomaticallu visible/
            findNavController().popBackStack()
        }


        binding.btnNext.setOnClickListener{
            val receiverName = et_receiver_name.text.toString()
            if (receiverName.isEmpty()){
                Toast.makeText(requireContext(),"Please enter receiver name", Toast.LENGTH_LONG).show()
                return@setOnClickListener //if receiverName is empty then return from here
            }
//            val navOption = NavOptions.Builder()
//                .setEnterAnim(R.anim.slide_in_right)
//                .setExitAnim(R.anim.slide_out_left)
//                .setPopEnterAnim(R.anim.slide_in_left)
//                .setPopExitAnim(R.anim.slide_out_right)
//                .build()
            //it is not type safe .ThereFore, we use safe args plugin
            //Bundles
            val args = Bundle()
            args.putString("receiverName",receiverName)

//            findNavController().navigate(R.id.sendCashFragment,args ,navOption )

//            safe args plugin is type safe and easy for fragment communication.
            // sending data to destination fragment.
            val action = ChooseReceiverFragmentDirections
                .actionChooseReceiverFragmentToSendCashFragment(receiverName)
            findNavController().navigate(action)

            //also generate explicit deep link to click this button
            //we need pending intent for this.Two ways
             val pendingIntent = findNavController()
                .createDeepLink()
                 .setGraph(R.navigation.main_nav_graph)
                 .setDestination(R.id.sendCashFragment)
                 .setArguments(args)
//                 .setArguments(SendCashFragmentArgs(receiverName).toBundle())
                 .createPendingIntent()

//            val pendingIntent = NavDeepLinkBuilder(requireContext())
//                .setGraph(R.navigation.main_nav_graph)
//                .setDestination(R.id.sendCashFragment)
//                .setArguments()
//                .createPendingIntent()

            showNotification(pendingIntent , receiverName)

        }
        return view
    }

    private fun showNotification(pendingIntent: PendingIntent, receiverName: String) {
        //create notification here
        val notification = NotificationCompat.Builder(requireContext(), CHANNAL_ID)
            .setSmallIcon(R.drawable.ic_notifications)
            .setContentTitle("Complete Transaction")
            .setContentText("Send money to $receiverName")
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()
        NotificationManagerCompat.from(requireContext()).notify(1002 , notification)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}