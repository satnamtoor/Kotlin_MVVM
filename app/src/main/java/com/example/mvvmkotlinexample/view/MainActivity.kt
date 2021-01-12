package com.example.mvvmkotlinexample.view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.os.AsyncTask
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cc.cloudist.acplibrary.ACProgressConstant
import cc.cloudist.acplibrary.ACProgressFlower
import com.example.mvvmkotlinexample.R
import com.example.mvvmkotlinexample.db.AppDatabase
import com.example.mvvmkotlinexample.db.EnqDB
import com.example.mvvmkotlinexample.db.InsertData
import com.example.mvvmkotlinexample.db.SendInsertStatus
import com.example.mvvmkotlinexample.utlis.MyBroadcastListener
import com.example.mvvmkotlinexample.utlis.Utility
import com.example.mvvmkotlinexample.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.list_display.*

public class MainActivity : AppCompatActivity(), SendInsertStatus, MyBroadcastListener {

    lateinit var context: Context
    private lateinit var adapter: PartAdapter
    private  lateinit var localAdapter:LocalAdapter
    lateinit var mainActivityViewModel: MainActivityViewModel
    lateinit var dialog: ACProgressFlower
    private var layoutManager: RecyclerView.LayoutManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_display)
        context = this@MainActivity
        dialog = ACProgressFlower.Builder(this@MainActivity)
            .direction(ACProgressConstant.DIRECT_CLOCKWISE)
            .themeColor(Color.WHITE)
            .bgColor(Color.TRANSPARENT)
            .bgAlpha(0f)
            .bgCornerRadius(0f)
            .fadeColor(Color.DKGRAY).build()
        dialog.setCanceledOnTouchOutside(false)
        dialog.show()
        mainActivityViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)


    }
    @SuppressLint("WrongConstant")
    override fun onResume() {
        super.onResume()

        mainActivityViewModel.getUser()!!.observe(this, Observer { serviceSetterGetter ->
            dialog.dismiss()
            layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
            if (Utility.isNetworkAvailable(this@MainActivity) === true) {
                adapter = PartAdapter(this, serviceSetterGetter.results)
                rv_list.adapter = adapter

                for (i in 0 until serviceSetterGetter.results.size) {
                    val itemDB = EnqDB()
                    itemDB.Name =
                        serviceSetterGetter?.results?.get(i)?.name?.first + " " + serviceSetterGetter?.results?.get(
                            i
                        )?.name?.last
                    itemDB.Age = serviceSetterGetter?.results?.get(i)?.dob?.age.toString()
                    itemDB.Address =
                        serviceSetterGetter?.results?.get(i)?.location?.street?.number.toString() + " " +
                                serviceSetterGetter?.results?.get(i)?.location?.street?.name.toString() + " " +
                                serviceSetterGetter?.results?.get(i)?.location?.city.toString() + " " +
                                serviceSetterGetter?.results?.get(i)?.location?.state.toString() + " " +
                                serviceSetterGetter?.results?.get(i)?.location?.country.toString()
                    itemDB.Profileimage= serviceSetterGetter?.results?.get(i)?.picture?.large

                                InsertData.onInsertProfile(
                                    this@MainActivity,
                                    itemDB,
                                    this@MainActivity
                                )
                }
            } else {
                getEnq(context)
            }

        })
    }
    override fun checkInsertStatusCallback(isSuccess: Boolean) {
        if (isSuccess == true) {
            Toast.makeText(this@MainActivity, "Insert successfully", Toast.LENGTH_LONG).show()
        }
    }

    @SuppressLint("WrongConstant")
    override fun internetCheck(value: String?) {
        if (value.equals("Yes"))
        {
            mainActivityViewModel.getUser()!!.observe(this, Observer { serviceSetterGetter ->
                dialog.dismiss()
                layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)

                    adapter = PartAdapter(this, serviceSetterGetter.results)
                    rv_list.adapter = adapter

                    for (i in 0 until serviceSetterGetter.results.size) {
                        val itemDB = EnqDB()
                        itemDB.Name =
                            serviceSetterGetter?.results?.get(i)?.name?.first + " " + serviceSetterGetter?.results?.get(
                                i
                            )?.name?.last
                        itemDB.Age = serviceSetterGetter?.results?.get(i)?.dob?.age.toString()
                        itemDB.Address =
                            serviceSetterGetter?.results?.get(i)?.location?.street?.number.toString() + " " +
                                    serviceSetterGetter?.results?.get(i)?.location?.street?.name.toString() + " " +
                                    serviceSetterGetter?.results?.get(i)?.location?.city.toString() + " " +
                                    serviceSetterGetter?.results?.get(i)?.location?.state.toString() + " " +
                                    serviceSetterGetter?.results?.get(i)?.location?.country.toString()
                        itemDB.Profileimage= serviceSetterGetter?.results?.get(i)?.picture?.large

                        InsertData.onInsertProfile(
                            this@MainActivity,
                            itemDB,
                            this@MainActivity
                        )

                }

            })
        }
        else{
            getEnq(context)
        }
    }

     fun getEnq(mcontext:Context) {
        class GetTasks :
            AsyncTask<Void?, Void?, List<EnqDB>>() {
            override fun onPreExecute() {
                super.onPreExecute()
                //dialog.show();
            }

            override fun onPostExecute(task: List<EnqDB>) {
                super.onPostExecute(task)
                if (!task.isEmpty()) {
                    localAdapter = LocalAdapter(mcontext, task)
                    rv_list.adapter = adapter
                }
            }


            override fun doInBackground(vararg params: Void?): List<EnqDB>? {
                return AppDatabase.getAppDatabase(this@MainActivity)?.medicineDB()
                    ?.getAll() as List<EnqDB>?
            }
        }

        val gt = GetTasks()
        gt.execute()
    }
}
