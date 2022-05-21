package com.example.ujian_network_and_design_patern_farhan_fadhilah.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ujian_network_and_design_patern_farhan_fadhilah.model.User
import com.example.ujian_network_and_design_patern_farhan_fadhilah.repository.UserRepository
import com.example.ujian_network_and_design_patern_farhan_fadhilah.room.UserDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RegisterViewModel(private val repository: UserRepository): ViewModel() {

    fun isLoading() = repository.loading
    fun register(user: User) = repository.register(user)

    override fun onCleared() {
        super.onCleared()
        repository.jobCleared()
    }

}