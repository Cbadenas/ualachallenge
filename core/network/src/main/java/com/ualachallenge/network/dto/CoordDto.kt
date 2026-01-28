package com.ualachallenge.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class CoordDto(
    val lon: Double,
    val lat: Double
)