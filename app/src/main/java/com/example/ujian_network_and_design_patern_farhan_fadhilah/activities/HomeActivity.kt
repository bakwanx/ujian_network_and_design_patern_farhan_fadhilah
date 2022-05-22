package com.example.ujian_network_and_design_patern_farhan_fadhilah.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ujian_network_and_design_patern_farhan_fadhilah.adapter.GenreAdapter
import com.example.ujian_network_and_design_patern_farhan_fadhilah.adapter.MovieAdapter
import com.example.ujian_network_and_design_patern_farhan_fadhilah.databinding.ActivityMainBinding
import com.example.ujian_network_and_design_patern_farhan_fadhilah.utils.showToast
import com.example.ujian_network_and_design_patern_farhan_fadhilah.viewmodel.MainViewModel
import com.example.ujian_network_and_design_patern_farhan_fadhilah.viewmodel.MovieViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by inject()
    private val movieViewModel: MovieViewModel by inject()
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var genreAdapter: GenreAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        genreAdapter = GenreAdapter()
        movieAdapter = MovieAdapter( this@HomeActivity)
        binding.apply {

            mainViewModel.getUser().observe(this@HomeActivity){ user ->
                tvName.setText(user.fullname)
            }

            ivLogout.setOnClickListener {
                mainViewModel.logOut()
                val intent = Intent(this@HomeActivity, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }

            movieViewModel.getGenre().observe(this@HomeActivity){ genre->

                val size = genre.genres.size
                genreAdapter.setItem(genre.genres)

                val layoutManager = LinearLayoutManager(this@HomeActivity, LinearLayoutManager.HORIZONTAL, false)
                rvGenre.layoutManager = layoutManager
                genreAdapter.notifyDataSetChanged()
                rvGenre.adapter = genreAdapter


            }

            movieViewModel.onLoading().observe(this@HomeActivity){
                if (it){
                    progresBar.visibility = View.VISIBLE
                }else{
                    progresBar.visibility = View.GONE
                }
            }

            fetchMovie()

//            if(genreAdapter.getSelected()?.name!!.isNotEmpty()){
//                Log.d("TAG", "genre: ${genreAdapter.getSelected()?.name}")
//                fetchMovie(id = genreAdapter.getSelected()!!.id)
//            }


        }

    }

    fun fetchMovie(idGenre: String = ""){
        movieViewModel.getMovies(idGenre).observe(this@HomeActivity){ movie ->
            movieAdapter.setItem(movie.results)
            binding.rvMovie.adapter = movieAdapter
        }
    }

    override fun onResume() {
        super.onResume()

    }
}