package com.example.jetpacknavigationcomponent

import android.app.Application
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import androidx.annotation.RequiresApi
import leakcanary.LeakCanary

const val  CHANNAL_ID = "demoChannel"

class MyApp:Application() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate() {
        super.onCreate()

        //Create notification channel here
        val channel = NotificationChannel(CHANNAL_ID , "Eample",NotificationManager.IMPORTANCE_DEFAULT)
        (getSystemService(NOTIFICATION_SERVICE) as NotificationManager).createNotificationChannel(channel)
    }
}