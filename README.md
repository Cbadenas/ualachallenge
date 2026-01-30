# Uala Cities Challenge

## Descripcion

- Decidi implementar una arquitectura Clean con aplicando el principio hexagonal basado en la idea 
  de tener una separacion lo mas clara posible, con la comunicacion entre capas bien definida.
  - Por que ?
    - Pense el proyecto como la base de una implementacion aun mayor.

- Usecases
  - Para el caso de los usecases, decidi que, para esta implementacion, solo sean un "pasa manos"
    entre los viewmodels y los repos. Si en el futuro los usecase requieren de logica mas compleja,
    la idea seria que los responsables sean estos usecases.


  
## added
- Initial commit. Initial Architecture created. Add cities.json file to network module.
- CitiesDataSource was added.
  - Change the way the repositories consumes and returns cities info.
  - getCityByCreiteria was added.
  - Tests were added.
- Usecases for getting all cities and filtered cities were added






