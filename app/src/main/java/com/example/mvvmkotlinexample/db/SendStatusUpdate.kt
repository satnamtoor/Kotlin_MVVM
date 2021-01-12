package com.example.mvvmkotlinexample.db

interface SendStatusUpdate {
    fun checkUpdateStatusCallback(isSuccess: Boolean)
}