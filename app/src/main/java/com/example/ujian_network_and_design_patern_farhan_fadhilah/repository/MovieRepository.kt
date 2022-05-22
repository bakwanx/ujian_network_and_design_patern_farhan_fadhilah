package com.example.ujian_network_and_design_patern_farhan_fadhilah.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.ujian_network_and_design_patern_farhan_fadhilah.api.ApiService
import com.example.ujian_network_and_design_patern_farhan_fadhilah.api.GenreResponse
import com.example.ujian_network_and_design_patern_farhan_fadhilah.api.MovieResponse
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieRepository {
    val errorMessage = MutableLiveData<String>()
    val genre = MutableLiveData<GenreResponse>()
    val movie = MutableLiveData<MovieResponse>()
    var job: Job? = null
    val loading = MutableLiveData<Boolean>()
    private val apiService: ApiService

   init {
       val retrofit =  Retrofit.Builder()
           .baseUrl("https://api.themoviedb.org/3/")
           .addConverterFactory(GsonConverterFactory.create())
           .build()
       apiService = retrofit.create(ApiService::class.java)
   }

    val exceptionHandler: CoroutineExceptionHandler = CoroutineExceptionHandler{_, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }


    fun getGenre(idGenre: String = ""): LiveData<GenreResponse>{
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = apiService.getGenre()
            if (response.isSuccessful){
                genre.postValue(response.body())
                loading.postValue(false)
                if (idGenre.isNotEmpty()){
                    getMovie(idGenre)
                }else{
                    getMovie(response.body()?.genres!!.get(0).id.toString())
                }
            }else{
                onError("Error: ${response.message()}")
            }
        }
        return genre
    }

    fun getMovie(idGenre: String = ""): LiveData<MovieResponse>{
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = apiService.getMovie(genre = idGenre)
            if (response.isSuccessful){
                movie.postValue(response.body())
                loading.postValue(false)
            }else{
                onError("Error: ${response.message()}")
            }
        }
        return movie
    }

    fun onError(message: String){
        errorMessage.postValue(message)
        loading.postValue(false)
    }

    fun jobCLeared(){
        job?.cancel()
    }
}