package com.example.ujian_network_and_design_patern_farhan_fadhilah.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ujian_network_and_design_patern_farhan_fadhilah.R
import com.example.ujian_network_and_design_patern_farhan_fadhilah.databinding.ActivityLoginBinding
import com.example.ujian_network_and_design_patern_farhan_fadhilah.utils.showToast
import com.example.ujian_network_and_design_patern_farhan_fadhilah.viewmodel.LoginViewModel
import org.koin.android.ext.android.inject

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val loginViewModel: LoginViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            loginViewModel.getIdUser().observe(this@LoginActivity){ idUser ->
                if (!idUser.isEmpty()){
                    val intent = Intent(this@LoginActivity, HomeActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }

            btnLogin.setOnClickListener {


                val email = edtEmailLogin.text.toString().trim()
                val password = edtPasswordLogin.text.toString().trim()
                if(checkInput(email, password)){
                    loginViewModel.login(email,password).observe(this@LoginActivity){ statusLogin ->
                        if (statusLogin){
                            val intent = Intent(this@LoginActivity, HomeActivity::class.java)
                            startActivity(intent)
                            finish()
                        }else{
                            showToast(this@LoginActivity, getString(R.string.toast_wrong_password))
                        }
                    }
                }else{
                    showToast(this@LoginActivity, getString(R.string.toast_failed_login))
                }

            }



            llToRegist.setOnClickListener {
                val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
                startActivity(intent)
            }
        }
    }

    fun checkInput(email: String, password: String): Boolean{
        if (email.isEmpty() || password.isEmpty()){
            showToast(this, getString(R.string.toast_check_form))
            return false
        } else{
            return  true
        }
    }

}