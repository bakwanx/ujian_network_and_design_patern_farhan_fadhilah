package com.example.ujian_network_and_design_patern_farhan_fadhilah.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class User(
    @PrimaryKey(autoGenerate = true) var userId: Int?,
    @ColumnInfo(name = "fullname") var fullname: String,
    @ColumnInfo(name = "email") var email: String,
    @ColumnInfo(name = "password") var password: String,
    @ColumnInfo(name = "gender") var gender: String
):Parcelable