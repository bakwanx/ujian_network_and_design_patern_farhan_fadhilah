package com.example.ujian_network_and_design_patern_farhan_fadhilah.repository

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.ujian_network_and_design_patern_farhan_fadhilah.model.User
import com.example.ujian_network_and_design_patern_farhan_fadhilah.room.UserDao
import com.example.ujian_network_and_design_patern_farhan_fadhilah.utils.Prefs.Companion.KEY_ID
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class UserRepository(
    private val userDao: UserDao,
    private val sharedPreferences: SharedPreferences
) {
    val loading = MutableLiveData<Boolean>()
    private var job: Job? = null
    private var statusLogin = MutableLiveData<Boolean>()
    private var _user = MutableLiveData<User>()
    private var idUser = MutableLiveData<String>()
    var user: LiveData<User> = _user

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
            val userData: User = userDao.getUserByEmail(email)
            if(!userData.password.isEmpty()){
                if (password.equals(userData.password)){
                    savePrefs(userData)
                    statusLogin.postValue(true)
                }else{
                    statusLogin.postValue(false)
                }
            }

        }
        return statusLogin
    }

    fun savePrefs(user: User){
        val editor = sharedPreferences.edit()
        editor.putString(KEY_ID, user.userId.toString())
        editor.apply()
    }

    fun getIdUser(): LiveData<String>{
        val userId = sharedPreferences.getString(KEY_ID,"")
        idUser.value = userId
        return idUser
    }

    fun getUserFromPrefs(): LiveData<User>{
        val userId = sharedPreferences.getString(KEY_ID,"")
        if(!userId.equals("")){
            job = CoroutineScope(Dispatchers.IO).launch {
                val userData = userDao.getUserById(Integer.parseInt(userId))
                _user.postValue(userData)
            }
        }
        return _user
    }

    fun clearPrefs(){
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }

    fun jobCleared(){
        job?.cancel()
    }

}