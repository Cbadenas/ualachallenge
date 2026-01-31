import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ualachallenge.location.SearchCitiesScreen
import com.ualachallenge.location.map.MapScreen

@Composable
fun MainNavigation(
    modifier: Modifier
) {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "search_cities") {

        // Pantalla de Búsqueda y Lista de Ciudades
        composable(route = "search_cities") {
            SearchCitiesScreen(
                modifier = modifier,
                onCitySelectedForMap = { city ->
                    // Esta lambda se ejecuta cuando se selecciona una ciudad en modo portrait.
                    // Navega a la ruta del mapa, pasando lat, lon y el nombre de la ciudad.
                    navController.navigate("map/${city.coord.lat}/${city.coord.lon}?name=${city.name}")
                }
            )
        }

        // Pantalla del Mapa
        composable(
            route = "map/{lat}/{lon}?name={name}", // Define los argumentos
            arguments = listOf(
                navArgument("lat") { type = NavType.FloatType },
                navArgument("lon") { type = NavType.FloatType },
                navArgument("name") {
                    type = NavType.StringType
                    nullable = true // El nombre puede ser nulo
                }
            )
        ) { backStackEntry ->
            // Extrae los argumentos de la ruta de navegación
            val arguments = requireNotNull(backStackEntry.arguments)
            val lat = arguments.getDouble("lat")
            val lon = arguments.getDouble("lon")
            val name = arguments.getString("name")

            MapScreen(
                lat = lat,
                lon = lon,
                cityName = name,
                onUpClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}
