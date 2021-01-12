package com.example.mvvmkotlinexample.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MedicineDao {
    //InstallerId
    @Query("SELECT * FROM ListTable")
    fun getAll(): List<EnqDB?>?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(medicineList: EnqDB?)

    @Query("DELETE FROM ListTable")
    fun delete()

    @Query("SELECT * FROM ListTable ORDER BY sno LIMIT 1")
    fun loadlastTask(): LiveData<EnqDB?>?

    @Query("UPDATE ListTable SET AcceptStatus = :status WHERE Name =:name")
    fun updateCheck(name: String?, status: String?)

}