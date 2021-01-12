package com.example.mvvmkotlinexample.viewmodel

import android.util.Log
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvmkotlinexample.model.ServicesSetterGetter
import com.example.mvvmkotlinexample.repository.MainActivityRepository
import com.example.mvvmkotlinexample.view.PartAdapter

 class MainActivityViewModel : ViewModel() {

    var servicesLiveData: MutableLiveData<ServicesSetterGetter>? = null



    fun getUser() : LiveData<ServicesSetterGetter>? {
        servicesLiveData = MainActivityRepository.getServicesApiCall()
        return servicesLiveData
    }
   /* fun getOnQueryTextChange(adapter: PartAdapter) : SearchView.OnQueryTextListener = object : SearchView.OnQueryTextListener{
        override fun onQueryTextChange(term: String?): Boolean {
            if (term != null) { filterList(term, adapter) }
            return false
        }
        override fun onQueryTextSubmit(term: String?): Boolean {
            if (term != null) { filterList(term, adapter) }
            return false
        }
    }
    fun filterList(term: String, adapter: PartAdapter) {

            adapter.filterList = adapter.originalList
            adapter.notifyDataSetChanged()


    }*/

}