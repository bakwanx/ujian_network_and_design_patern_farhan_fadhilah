package com.example.ujian_network_and_design_patern_farhan_fadhilah.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ujian_network_and_design_patern_farhan_fadhilah.adapter.GenreAdapter
import com.example.ujian_network_and_design_patern_farhan_fadhilah.model.Genre
import com.example.ujian_network_and_design_patern_farhan_fadhilah.repository.MovieRepository

class MovieViewModel(private val repository: MovieRepository): ViewModel() {

    fun getGenre() = repository.getGenre()
    fun getMovies(idGenre: String) = repository.getMovie(idGenre = idGenre)
    fun onLoading() = repository.loading


    override fun onCleared() {
        super.onCleared()
        repository.cleared()
    }
}