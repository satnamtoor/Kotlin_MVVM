package com.example.mvvmkotlinexample.db

import android.content.Context
import android.os.AsyncTask
import android.util.Log
import com.example.mvvmkotlinexample.db.AppDatabase.Companion.getAppDatabase

/**
 * Created by satnamsingh on 23/05/18.
 */
object InsertData {
    var TAG = "InsertData"
    fun onInsertProfile(
        context: Context?,
        itemDB: EnqDB?, callback: SendInsertStatus
    ) {
        insertPlayerProfile(context, itemDB, callback).execute()
    }

    class insertPlayerProfile(
        var context: Context?,
        var itemDB: EnqDB?,
        var callback: SendInsertStatus
    ) : AsyncTask<Void?, Void?, Void?>() {


        override fun onPostExecute(aVoid: Void?) {
            super.onPostExecute(aVoid)
            callback.checkInsertStatusCallback(true)
        }

        override fun onPreExecute() {
            Log.d(TAG, "onPreExecute loading...: ")
        }
        
        override fun doInBackground(vararg params: Void?): Void? {
            getAppDatabase(context)!!.medicineDB()!!.insertAll(itemDB)
            return null
        }
    }
}