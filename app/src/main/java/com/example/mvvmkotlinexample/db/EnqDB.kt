package com.example.mvvmkotlinexample.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "ListTable")
class EnqDB : Serializable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "sno")
    var sno = 0

    @ColumnInfo(name = "Name")
    var Name: String? = null
        set(Name) {
            var Name = Name
            Name = Name
        }

    @ColumnInfo(name = "Age")
    var Age: String? = null
        set(Age) {
            var Age = Age
            Age = Age
        }

    @ColumnInfo(name = "Address")
    var Address: String? = null
        set(Address) {
            var Address = Address
            Address = Address
        }

    @ColumnInfo(name = "AcceptStatus")
    var AcceptStatus: String? = null
        set(AcceptStatus) {
            var AcceptStatus = AcceptStatus
            AcceptStatus = AcceptStatus
        }


    @ColumnInfo(name = "Profileimage")
    var Profileimage: String? = null
        set(Profileimage) {
            var Profileimage = Profileimage
            Profileimage = Profileimage
        }
}