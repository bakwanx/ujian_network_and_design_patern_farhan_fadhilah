package com.example.ujian_network_and_design_patern_farhan_fadhilah.utils

import android.app.Application
import android.content.Context
import android.content.SharedPreferences

class Prefs {

    fun getSharedPrefs(androidApplication: Application): SharedPreferences {
        return androidApplication.getSharedPreferences("kotlinsharedpreference", Context.MODE_PRIVATE)
    }

    companion object{
        const val KEY_ID = "key_id"
    }
}