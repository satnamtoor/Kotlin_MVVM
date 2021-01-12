package com.example.mvvmkotlinexample.utlis

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager

class NetworkChangeReceiver(listener: MyBroadcastListener) : BroadcastReceiver() {
    private val listener: MyBroadcastListener
    override fun onReceive(context: Context, intent: Intent) {
        try {
            if (isOnline(context)) {
                listener.internetCheck("Yes")
            } else {
                listener.internetCheck("No")
            }
        } catch (e: NullPointerException) {
            e.printStackTrace()
        }
    }

    private fun isOnline(context: Context): Boolean {
        return try {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val netInfo = cm.activeNetworkInfo
            netInfo != null && netInfo.isConnected
        } catch (e: NullPointerException) {
            e.printStackTrace()
            false
        }
    }
    init {
        this.listener = listener
    }
}