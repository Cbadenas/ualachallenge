# Uala Cities Challenge

## Descripcion

- Decidi implementar una arquitectura Clean con aplicando el principio hexagonal basado en la idea 
  de tener una separacion lo mas clara posible, con la comunicacion entre capas bien definida.
  - Por que ?
    - Pense el proyecto como la base de una implementacion aun mayor.

- ### Usecases
  - Para el caso de los usecases, decidi que, para esta implementacion, solo sean un "pasa manos"
    entre los viewmodels y los repos. Si en el futuro los usecase requieren de logica mas compleja,
    la idea seria que los responsables sean estos usecases.

- ViewModel
  - Para la conexion entre la vista y el viewmodel, decidi usar el patron MVI.

- Views
  - ### MainActivity -> Cree una vista principal que contiene un Navigation
  - ### MainNavigation -> Define la forma en la que se realiza la navegacion
    - Cree una pantalla llamada SearchCitiesScreen que define 2 composables segun orientacion
    - ### PortraitLayout
      - Muestra un Search en la parte superior
        y debajo, la lista de todas las ciudades.
        Cuando se hace una busqueda, se filtra la lista acorde a la especificacion.
        Al seleccionar una lista, si estamos en modo portrait, se navega a la una pantalla que
        muestra el mapa en las coordenadas de la ciudad.
    - ### LandscapeLayout 
      - Se muestra la lista de ciudades a la izquierda y a la derecha
        el mapa, segun los wireframes.
      

  
## added
- Initial commit. Initial Architecture created. Add cities.json file to network module.
- CitiesDataSource was added.
  - Change the way the repositories consumes and returns cities info.
  - getCityByCreiteria was added.
  - Tests were added.
- Usecases for getting all cities and filtered cities were added
- List of Cities screen was added.
  - ViewModel to connect View with Usecase was created
  - All compoents were connected
- Screen based on wireframes were added.
  - SearchScreen that contains both Portrait and Landscape screen
  - MapScreen
  - Screens were connected to viewmodel. 
  - Tests for Usecases
  - Fix issue with Lat and Long order when selecting a city.






