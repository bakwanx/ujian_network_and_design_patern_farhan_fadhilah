package com.example.ujian_network_and_design_patern_farhan_fadhilah

import android.app.Application
import androidx.room.Room
import com.example.ujian_network_and_design_patern_farhan_fadhilah.repository.MovieRepository
import com.example.ujian_network_and_design_patern_farhan_fadhilah.utils.Prefs
import com.example.ujian_network_and_design_patern_farhan_fadhilah.repository.UserRepository
import com.example.ujian_network_and_design_patern_farhan_fadhilah.room.UserDatabase
import com.example.ujian_network_and_design_patern_farhan_fadhilah.viewmodel.LoginViewModel
import com.example.ujian_network_and_design_patern_farhan_fadhilah.viewmodel.MainViewModel
import com.example.ujian_network_and_design_patern_farhan_fadhilah.viewmodel.MovieViewModel
import com.example.ujian_network_and_design_patern_farhan_fadhilah.viewmodel.RegisterViewModel
import org.koin.android.ext.android.startKoin
import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

class MyApp: Application() {
    override fun onCreate() {
        super.onCreate()

        val movieModule = module {
            viewModel {
                MovieViewModel(get())
            }
            single {
                MovieRepository()
            }
        }

        val userModule = module {
            viewModel {
                MainViewModel(get())
            }

            viewModel {
                RegisterViewModel(get())
            }

            viewModel {
                LoginViewModel(get())
            }

            single {
                Room.databaseBuilder(
                    androidApplication(),
                    UserDatabase::class.java,
                    "User"
                ).build()
            }

            factory {
                val database = get<UserDatabase>()
                database.userDao()
            }

            single {
                UserRepository(get(), Prefs().getSharedPrefs(androidApplication()))
            }

        }

        startKoin(this, listOf(
            userModule,
            movieModule
        ))

    }



}