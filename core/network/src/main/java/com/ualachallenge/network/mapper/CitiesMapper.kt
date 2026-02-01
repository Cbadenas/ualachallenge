package com.ualachallenge.network.mapper

import com.ualachallenge.domain.city.City
import com.ualachallenge.domain.city.Coord
import com.ualachallenge.network.dto.CityDto

fun CityDto.toDomain(isFavorite: Boolean = false): City {
    return City(
        id = this.id,
        name = this.name,
        country = this.country,
        coord = Coord(this.coord.lon, this.coord.lat),
        isFavorite = isFavorite
    )
}