package com.ualachallenge.domain.city

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class City(
    val country: String,
    val name: String,
    val id: Int,
    val coord: Coord
) : Parcelable