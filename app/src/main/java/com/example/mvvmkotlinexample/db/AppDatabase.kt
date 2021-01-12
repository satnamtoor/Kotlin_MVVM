package com.example.mvvmkotlinexample.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [EnqDB::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun medicineDB(): MedicineDao?

    companion object {
        private var INSTANCE: AppDatabase? = null
        @JvmStatic
        fun getAppDatabase(context: Context?): AppDatabase? {
            val DATABASE_NAME = "shaadi-db"
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context!!, AppDatabase::class.java, DATABASE_NAME
                )
                    .build()
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}