package com.example.mvvmkotlinexample.utlis

import android.Manifest
import android.R
import android.content.Context
import android.graphics.Color
import android.graphics.Matrix
import android.graphics.Rect
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.net.Uri
import android.os.Environment
import android.os.Handler
import android.util.Base64
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.loader.content.CursorLoader
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream
import java.sql.Time
import java.text.Format
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import java.util.regex.Matcher
import java.util.regex.Pattern

/**
 * Created by Satnam on 5/24/2017.
 */
object Utility {

    fun isNetworkAvailable(context: Context): Boolean {
        val cm: ConnectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo: NetworkInfo = cm.getActiveNetworkInfo()
        return networkInfo != null && networkInfo.isConnected()
    }


}