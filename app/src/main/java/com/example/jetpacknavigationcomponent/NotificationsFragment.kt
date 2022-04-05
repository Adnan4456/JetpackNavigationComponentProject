package com.example.jetpacknavigationcomponent

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jetpacknavigationcomponent.Adapters.NotificationAdapter
import com.example.jetpacknavigationcomponent.databinding.FragmentNotificationsBinding

class NotificationsFragment: Fragment() {

    private var _binding:FragmentNotificationsBinding? = null
    private val binding get() = _binding!!

    private lateinit var notificationList: NotificationAdapter
    private var   notifications = mutableListOf<String>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentNotificationsBinding.inflate(layoutInflater,container,false)
        val view = binding.root
        getNotification()

        binding.recyclerView.adapter = NotificationAdapter(notifications)
        //setting layout on Recycler View
        binding.recyclerView.layoutManager = LinearLayoutManager(context)

        return view
    }

    private fun getNotification(): List<String>{

        notifications = mutableListOf<String>()

        for (i in 1..20){
            notifications.add("Notifcation # $i")
           // Log.d("Notification = ", "$i")
        }
        return notifications
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}