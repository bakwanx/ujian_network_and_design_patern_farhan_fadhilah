package com.example.ujian_network_and_design_patern_farhan_fadhilah.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ujian_network_and_design_patern_farhan_fadhilah.R
import com.example.ujian_network_and_design_patern_farhan_fadhilah.databinding.ActivityMovieDetailBinding
import com.example.ujian_network_and_design_patern_farhan_fadhilah.databinding.ActivityUserProfileBinding
import com.example.ujian_network_and_design_patern_farhan_fadhilah.model.User
import com.example.ujian_network_and_design_patern_farhan_fadhilah.viewmodel.MainViewModel
import org.koin.android.ext.android.inject

class UserProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUserProfileBinding
    private val mainViewModel: MainViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            mainViewModel.getUser().observe(this@UserProfileActivity){ user ->
                edtEmail.setText(user.email)
                edtFullname.setText(user.fullname)
                tvGender.setText(user.gender)

                btnUpdate.setOnClickListener {
                    if(edtEmail.text.toString().isNotEmpty() || edtFullname.text.toString().isNotEmpty()){
                        var objUser = User(
                            userId = user.userId,
                            email = edtEmail.text.toString(),
                            fullname = edtFullname.text.toString(),
                            password = user.password,
                            gender = user.gender
                        )
                        mainViewModel.updateUser(objUser)
                        val intent = Intent(this@UserProfileActivity, HomeActivity::class.java)
                        startActivity(intent)
                        finish()
                    }

                }
            }


        }
    }
}