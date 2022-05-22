package com.example.ujian_network_and_design_patern_farhan_fadhilah.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ujian_network_and_design_patern_farhan_fadhilah.model.User
import com.example.ujian_network_and_design_patern_farhan_fadhilah.repository.UserRepository
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: UserRepository): ViewModel() {

    private lateinit var user: User

    fun login(email: String, password: String) = repository.login(email, password)

    fun getUser() = repository.getUserFromPrefs()

    fun getIdUser() = repository.getIdUser()

    override fun onCleared() {
        super.onCleared()
        repository.jobCleared()
    }
}