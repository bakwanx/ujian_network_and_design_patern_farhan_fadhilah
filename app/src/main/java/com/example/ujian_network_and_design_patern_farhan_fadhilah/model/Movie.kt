package com.example.ujian_network_and_design_patern_farhan_fadhilah.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Movie(
    @SerialName("backdrop_path")
    val backdropPath: String,

    @SerialName("genre_ids")
    val genreIDS: List<Long>,

    @SerialName("original_language")
    val originalLanguage: String,

    @SerialName("original_title")
    val originalTitle: String,

    @SerialName("poster_path")
    val posterPath: String,

    val video: Boolean,

    @SerialName("vote_average")
    val voteAverage: Double,

    @SerialName("vote_count")
    val voteCount: Long,

    val overview: String,

    @SerialName("release_date")
    val releaseDate: String,

    val title: String,
    val id: Long,
    val adult: Boolean,
    val popularity: Double,

    @SerialName("media_type")
    val mediaType: String,
)