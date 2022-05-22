package com.example.ujian_network_and_design_patern_farhan_fadhilah.activities

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.ujian_network_and_design_patern_farhan_fadhilah.R
import com.example.ujian_network_and_design_patern_farhan_fadhilah.adapter.MovieAdapter
import com.example.ujian_network_and_design_patern_farhan_fadhilah.adapter.MovieAdapter.Companion.KEY_ID_GENRE
import com.example.ujian_network_and_design_patern_farhan_fadhilah.adapter.MovieAdapter.Companion.KEY_MOVIE
import com.example.ujian_network_and_design_patern_farhan_fadhilah.databinding.ActivityMovieDetailBinding
import com.example.ujian_network_and_design_patern_farhan_fadhilah.model.Movie
import com.example.ujian_network_and_design_patern_farhan_fadhilah.viewmodel.MovieViewModel
import org.koin.android.ext.android.inject
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class MovieDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMovieDetailBinding
    private val movieViewModel: MovieViewModel by inject()
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        movieAdapter = MovieAdapter( this@MovieDetailActivity)

        binding.apply {
            val movie = intent.getSerializableExtra(KEY_MOVIE) as Movie
            val parser =  SimpleDateFormat("yyyy-MM-dd")
            val formatter = SimpleDateFormat("dd-MM-yyyy")
            val formattedDate = formatter.format(parser.parse(movie.getReleaseDate()))
            Glide.with(this@MovieDetailActivity)
                .load("https://image.tmdb.org/t/p/w342${movie.getPosterPath()}")
                .centerCrop()
                .placeholder(R.drawable.ic_no_img)
                .into(ivPoster)
            Glide.with(this@MovieDetailActivity)
                .load("https://image.tmdb.org/t/p/w342${movie.getBackdropPath()}")
                .centerCrop()
                .placeholder(R.drawable.ic_no_img)
                .into(ivBackground)
            tvMovieTitle.setText(movie.getTitle())
            tvSynopsis.setText(movie.getOverview())
            tvPopularity.setText(movie.getPopularity().toString())
            tvRating.setText(movie.getVoteAverage().toString())
            tvMovieRelease.setText(formattedDate)

            Log.d("TAG", "pesan: ${movie.getPopularity().toString()} vote: ${movie.getVoteAverage().toString()}")
            val idGenre = intent.getStringExtra(KEY_ID_GENRE)
            movieViewModel.getMovies(idGenre.toString()).observe(this@MovieDetailActivity){ movie ->

                val layoutManager = LinearLayoutManager(this@MovieDetailActivity, LinearLayoutManager.HORIZONTAL, false)
                rvRecomendation.layoutManager = layoutManager
                movieAdapter.setItem(movie.results)
                rvRecomendation.adapter = movieAdapter
                rvRecomendation.isNestedScrollingEnabled = false
            }
        }
    }
}