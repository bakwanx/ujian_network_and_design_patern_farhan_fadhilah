package com.example.ujian_network_and_design_patern_farhan_fadhilah.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ujian_network_and_design_patern_farhan_fadhilah.repository.UserRepository
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: UserRepository): ViewModel() {

    fun login(email: String, password: String) = repository.login(email, password)

}