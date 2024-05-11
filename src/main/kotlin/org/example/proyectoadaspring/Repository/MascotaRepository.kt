package org.example.proyectoadaspring.Repository

import org.example.proyectoadaspring.Mascota
import org.example.proyectoadaspring.Persona
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

/**
 * Repositorio que determina las operaciones que se van a realizar en la base de datos de mascotas
 */
@Repository
interface MascotaRepository : CrudRepository<Mascota?, Int?> {
    /**
     * Busca a una mascota a partir de su id
     *
     * @return datos de la mascota
     */
    fun findByIdMascota(id:Int): Mascota?

    /**
     * Busca a una mascota a partir de su nombre
     *
     * @return datos de las mascotas con ese nombre
     */
    fun findByNombreMascota(nombreMascota: String): List<Mascota?>

    /**
     * Busca a una mascota a partir del dni de su dueño
     *
     * @return datos de las mascotas encontradas
     */
    fun findByDueno(dueno: Persona): List<Mascota?>

    /**
     * Busca a una mascota a partir del tipo
     *
     * @return datos de las mascotas de dicho tipo
     */
    fun findByTipo(tipo: String): List<Mascota?>
}