package com.example.ujian_network_and_design_patern_farhan_fadhilah.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.ujian_network_and_design_patern_farhan_fadhilah.R
import com.example.ujian_network_and_design_patern_farhan_fadhilah.databinding.ActivityRegisterBinding
import com.example.ujian_network_and_design_patern_farhan_fadhilah.model.User
import com.example.ujian_network_and_design_patern_farhan_fadhilah.room.UserDatabase
import com.example.ujian_network_and_design_patern_farhan_fadhilah.utils.showToast
import com.example.ujian_network_and_design_patern_farhan_fadhilah.viewmodel.RegisterViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding : ActivityRegisterBinding
    private val registerViewModel: RegisterViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            btnRegister.setOnClickListener {
                val fullname = edtFullname.text.toString()
                val email = edtEmail.text.toString().trim()
                val password = edtPassword.text.toString().trim()
                val confirmPassword = edtConfirmPassword.text.toString().trim()
                val gender = when (radioGroupGender.checkedRadioButtonId){
                    radioBtnMale.id -> getString(R.string.male)
                    radioBtnFemale.id -> getString(R.string.female)
                    else -> ""
                }
                if(checkInput(fullname, email, password, confirmPassword, gender)){
                    val userModel = User(userId = null, fullname, email, password, gender)
                    registerViewModel.register(userModel)
                    val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                    startActivity(intent)
                    finish()

                }else{
                    showToast(this@RegisterActivity, getString(R.string.toast_failed_register))
                }
            }

            registerViewModel.isLoading().observe(this@RegisterActivity){loading ->
                if(loading){
                    progressbar.visibility = View.VISIBLE
                }else{
                    progressbar.visibility = View.GONE
                }
            }

            llToLogin.setOnClickListener {
                val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                startActivity(intent)
            }
        }
    }

    fun checkInput(fullname: String, email: String, password: String, confirmPassword: String, gender: String): Boolean{
        if (fullname.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || gender.isEmpty()){
            showToast(this, getString(R.string.toast_check_form))
            return false
        } else if (!password.equals(confirmPassword)){
            showToast(this, getString(R.string.toast_confirm_password))
            return false
        }else{
            return  true
        }
    }
}