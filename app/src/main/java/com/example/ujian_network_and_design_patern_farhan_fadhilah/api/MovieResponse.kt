package com.example.ujian_network_and_design_patern_farhan_fadhilah.api

import com.example.ujian_network_and_design_patern_farhan_fadhilah.model.Movie
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieResponse(
    val page: Long,
    val results: List<Movie>,
)