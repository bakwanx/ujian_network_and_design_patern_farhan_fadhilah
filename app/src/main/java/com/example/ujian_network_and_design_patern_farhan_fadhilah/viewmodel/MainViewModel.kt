package com.example.ujian_network_and_design_patern_farhan_fadhilah.viewmodel

import androidx.lifecycle.ViewModel
import com.example.ujian_network_and_design_patern_farhan_fadhilah.repository.UserRepository

class MainViewModel(private val repository: UserRepository): ViewModel() {

    fun getUser() = repository.getUserFromPrefs()

    fun logOut() = repository.clearPrefs()

    override fun onCleared() {
        super.onCleared()
        repository.jobCleared()
    }
}