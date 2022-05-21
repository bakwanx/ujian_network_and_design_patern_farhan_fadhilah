package com.example.ujian_network_and_design_patern_farhan_fadhilah.repository

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.ujian_network_and_design_patern_farhan_fadhilah.model.User
import com.example.ujian_network_and_design_patern_farhan_fadhilah.room.UserDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class UserRepository(
    private val userDao: UserDao
) {
    val loading = MutableLiveData<Boolean>()
    private var job: Job? = null
    private var statusLogin = MutableLiveData<Boolean>()

    fun register(user: User){
        job = CoroutineScope(Dispatchers.IO).launch {

            val response = userDao.register(user)
            if(response != 0.toLong()){
                loading.postValue(false)
            }else{
                loading.postValue(true)
            }
        }
    }

    fun login(email: String, password: String): LiveData<Boolean>{
        job = CoroutineScope(Dispatchers.IO).launch {
            val userData = userDao.getUserByEmail(email)

            if (password.equals(userData.password)){
                statusLogin.postValue(true)
            }else{
                statusLogin.postValue(false)
            }
        }
        return statusLogin
    }

    fun jobCleared(){
        job?.cancel()
    }

}