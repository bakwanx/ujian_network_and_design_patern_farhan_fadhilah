package com.example.ujian_network_and_design_patern_farhan_fadhilah.api

import com.example.ujian_network_and_design_patern_farhan_fadhilah.model.Genre
import kotlinx.serialization.Serializable

@Serializable
data class GenreResponse(
    val genres: List<Genre>
)