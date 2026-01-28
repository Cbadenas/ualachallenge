package com.ualachallenge.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CityDto(
    val country: String,
    val name: String,
    @SerialName("_id")
    val id: Int,
    val coord: CoordDto
)