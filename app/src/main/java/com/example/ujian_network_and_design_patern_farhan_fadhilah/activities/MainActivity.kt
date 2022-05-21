package com.example.ujian_network_and_design_patern_farhan_fadhilah.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.ujian_network_and_design_patern_farhan_fadhilah.R
import com.example.ujian_network_and_design_patern_farhan_fadhilah.room.UserDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private var userDb: UserDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        userDb = UserDatabase.getInstance(this)

        GlobalScope.launch {
            val checkUser = userDb?.userDao()?.getAllUser()
            if(checkUser != null){
                Log.d("TAG", "pesan akun: ${checkUser}")
            }else{
                Log.d("TAG", "pesan akun: tidak ada ")
            }
        }
    }
}