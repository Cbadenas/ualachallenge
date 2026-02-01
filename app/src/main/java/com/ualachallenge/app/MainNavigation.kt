package com.ualachallenge.app

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ualachallenge.location.SearchCitiesScreen
import com.ualachallenge.location.details.CityDetailScreen
import com.ualachallenge.location.map.MapScreen

@Composable
fun MainNavigation(
    modifier: Modifier
) {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "search_cities") {

        composable(route = "search_cities") {
            SearchCitiesScreen(
                modifier = modifier,
                onCitySelectedForMap = { city ->
                    navController.navigate("map/${city.id}/${city.coord.lat}/${city.coord.lon}?name=${city.name}")
                },
                onNavigateToDetails = { cityId ->
                    navController.navigate("city_details/$cityId")
                }
            )
        }

        composable(
            route = "map/{cityId}/{lat}/{lon}?name={name}",
            arguments = listOf(
                navArgument("cityId") { type = NavType.IntType },
                navArgument("lat") { type = NavType.FloatType },
                navArgument("lon") { type = NavType.FloatType },
                navArgument("name") {
                    type = NavType.StringType
                    nullable = true // El nombre puede ser nulo
                }
            )
        ) { backStackEntry ->
            val arguments = requireNotNull(backStackEntry.arguments)
            val cityId = arguments.getInt("cityId")
            val lat = arguments.getFloat("lat").toDouble()
            val lon = arguments.getFloat("lon").toDouble()
            val name = arguments.getString("name")

            MapScreen(
                lat = lat,
                lon = lon,
                cityName = name,
                onUpClick = {
                    navController.popBackStack()
                },
                onNavigateToDetails = {
                    navController.navigate("city_details/$cityId")
                }
            )
        }

        composable(
            route = "city_details/{cityId}",
            arguments = listOf(navArgument("cityId") { type = NavType.IntType })
        ) { backStackEntry ->
            val cityId = backStackEntry.arguments?.getInt("cityId") ?: -1
            CityDetailScreen(
                cityId = cityId,
                onUpClick = { navController.popBackStack() }
            )
        }
    }
}
