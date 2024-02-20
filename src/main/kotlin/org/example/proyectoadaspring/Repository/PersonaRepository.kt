package org.example.proyectoadaspring.Repository

import org.example.proyectoadaspring.Persona
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

/**
 * Repositorio que determina las operaciones que se van a realizar en la base de datos de personas
 */
@Repository
interface PersonaRepository : CrudRepository<Persona?, Int?> {
    /**
     * Busca a una persona a partir de su dni
     *
     * @return datos de la persona
     */
    fun findByDni(dni:String): Persona?

    /**
     * Busca a una persona a partir de su nombre
     *
     * @return datos de las personas con dicho nombre
     */
    fun findByNombre(nombre: String): List<Persona?>

    /**
     * Busca a una persona a partir de su teléfono
     *
     * @return datos de la persona
     */
    fun findByTelefono(telf: Int): Persona?

    /**
     * Busca a una persona a partir de su dirección
     *
     * @return datos de la persona
     */
    fun findByDireccion(dir: String): Persona?
}