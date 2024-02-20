package org.example.proyectoadaspring.Repository

import org.example.proyectoadaspring.Mascota
import org.example.proyectoadaspring.Prueba
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.time.LocalDate

/**
 * Repositorio que determina las operaciones que se van a realizar en la base de datos de pruebas
 */
@Repository
interface PruebaRepository : CrudRepository<Prueba?, Int?>{
    /**
     * Busca a una prueba a partir de su id
     *
     * @return datos de la prueba
     */
    fun findByidPrueba(id:Int): Prueba?

    /**
     * Busca a una prueba a partir de su nombre
     *
     * @return datos de las pruebas con dicho nombre
     */
    fun findBynombrePrueba(nombre: String): List<Prueba?>

    /**
     * Busca a una prueba a partir del id de la mascota a la que se le realiza
     *
     * @return datos de las pruebas de dicha mascota
     */
    fun findByMascota(mascota: Mascota): List<Prueba?>

    /**
     * Busca a una prueba a partir de su fecha de realización
     *
     * @return datos de las pruebas que se realizaron en esa fecha
     */
    fun findByFechaRealizada(fecha: String): List<Prueba?>
}