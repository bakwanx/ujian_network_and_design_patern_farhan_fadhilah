package com.example.ujian_network_and_design_patern_farhan_fadhilah.model

import kotlinx.serialization.Serializable

@Serializable
data class Genre (
    val id: Long,
    val name: String,
    var isSelected: Boolean
)
