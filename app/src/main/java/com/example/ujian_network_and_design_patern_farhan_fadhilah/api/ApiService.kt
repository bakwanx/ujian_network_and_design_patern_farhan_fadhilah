package com.example.ujian_network_and_design_patern_farhan_fadhilah.api

import android.util.Log
import com.example.ujian_network_and_design_patern_farhan_fadhilah.model.Movie
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    companion object{
        val API_KEY = "fe8bf5cb585bc3e88c29b9f9dc972f14"
    }

    @GET("genre/movie/list")
    suspend fun getGenre(
        @Query("api_key") apiKey: String = API_KEY
    ): Response<GenreResponse>

    @GET("discover/movie")
    suspend fun getMovie(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("page") page: Int = 1,
        @Query("with_genres") genre: String
    ): Response<MovieResponse>
}