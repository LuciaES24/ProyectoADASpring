package org.example.proyectoadaspring.Service

import org.example.proyectoadaspring.Mascota
import org.example.proyectoadaspring.Persona
import org.example.proyectoadaspring.Repository.MascotaRepository
import org.example.proyectoadaspring.Repository.PersonaRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * Servicio que maneja los datos de las personas, utilizando las funciones del repositorio
 *
 * @property mascotaRepository repositorio que determina las funciones de las mascotas
 * @property personaRepository repositorio que determina las funciones de las personas
 */
@Service
class PersonaService {
    @Autowired
    private val personaRepository : PersonaRepository? = null
    @Autowired
    private val mascotaRepository : MascotaRepository? = null

    /**
     * Pide los datos necesarios al controlador para añadir una persona a la base de datos
     * @param dni dni de la persona que vamos a registrar
     * @param nombre nombre de la persona que vamos a registrar
     * @param telefono teléfono de la persona que vamos a registrar
     * @param direccion dirección de la persona que vamos a registrar
     *
     * @return cadena con el resultado de la operación
     */
    fun addNewPersona(dni: String?, nombre: String?, telefono: String?, direccion: String?): String {
        var resultado: String
        try {
            if (dni == "" || nombre == "" || telefono == "" || direccion == ""){
                throw Exception()
            }else{
                val persona = Persona()
                persona.dni = dni
                persona.nombre = nombre
                persona.telefono = telefono!!.toInt()
                persona.direccion = direccion
                personaRepository!!.save(persona)
                resultado = "Datos guardados correctamente"
            }
        }catch (e:Exception){
            resultado = "No se han podido registrar la persona"
        }
        return resultado
    }

    /**
     * Pide los datos necesarios al controlador para actualizar una persona de la base de datos
     * @param dni dni de la persona a la que vamos a modificar los datos
     * @param nombre nuevo nombre de la persona que vamos a actualizar
     * @param telefono nuevo teléfono de la persona que vamos a actualizar
     * @param direccion nueva dirección de la persona que vamos a actualizar
     *
     * @return cadena con el resultado de la operación
     */
    fun updatePerson(dni: String?, nombre: String?, direccion: String?, telefono:String?): String {
        var resultado: String
        try {
            val persona = getPersonaByDni(dni)
            if (persona == null || dni == "" || nombre == "" || direccion == "" || telefono == ""){
                throw Exception()
            }else{
                persona.nombre = nombre
                persona.direccion = direccion
                persona.telefono = telefono!!.toInt()
                personaRepository!!.save(persona)
                resultado = "Actualizado"
            }
        }catch (e: Exception){
            resultado = "No se han podido actualizar los datos"
        }
        return resultado
    }

    /**
     * Pide el dni al controlador para eliminar a una persona de la base de datos
     * @param dni dni de la persona que vamos a eliminar de la base de datos
     *
     * @return cadena con el resultado de la operación
     */
    fun delPerson(dni: String?): String {
        var resultado: String
        try {
            val persona = getPersonaByDni(dni)
            if (persona == null){
                throw Exception()
            }else{
                personaRepository!!.delete(persona)
                for (mascota in persona.mascotas!!){
                    val mascotaFind = getMascotaById(mascota.idMascota)
                    if (mascotaFind != null){
                        mascotaRepository!!.delete(mascotaFind)
                    }
                }
                resultado = "Eliminado"
            }
        }catch (e:Exception){
            resultado = "No se ha podido eliminar la persona"
        }
        return resultado
    }

    /**
     * Busca a la mascota de la base de datos por id
     * @param id id de la mascota que queremos buscar
     *
     * @return cadena con el resultado de la operación
     */
    fun getMascotaById(id: Int?): Mascota?{
        return if (id == null) {
            Mascota()
        } else {
            mascotaRepository!!.findByIdMascota(id)
        }
    }

    /**
     * Busca a todas las personas de la base de datos
     *
     * @return cadena con el resultado de la operación
     */
    fun getPersonas(): MutableIterable<Persona?> {
        return personaRepository!!.findAll()
    }

    /**
     * Busca a una persona de la base de datos a partir del dni
     * @param dni dni de la persona que queremos buscar
     *
     * @return cadena con el resultado de la operación
     */
    fun getPersonaByDni(dni: String?): Persona? {
        return if (dni.isNullOrEmpty()) {
            Persona()
        } else {
            personaRepository!!.findByDni(dni)
        }
    }

    /**
     * Busca una persona de la base de datos a partir de un teléfono
     * @param telefono teléfono de la persona que queremos buscar
     *
     * @return cadena con el resultado de la operación
     */
    fun getPersonaByTelefono(telefono: String?): Persona? {
        return if (telefono.isNullOrEmpty()) {
            Persona()
        } else {
            personaRepository!!.findByTelefono(telefono.toInt())
        }
    }

    /**
     * Busca a todas las personas de la base de datos a partir de una dirección
     * @param direccion dirección de la persona que queremos buscar
     *
     * @return cadena con el resultado de la operación
     */
    fun getPersonaByDireccion(direccion: String?): Persona? {
        return if (direccion.isNullOrEmpty()) {
            Persona()
        } else {
            personaRepository!!.findByDireccion(direccion)
        }
    }

    /**
     * Busca a todas las personas de la base de datos a partir de un nombre
     * @param nombre nombre de las personas que queremos buscar
     *
     * @return cadena con el resultado de la operación
     */
    fun getPersonaByNombre(nombre: String?): Iterable<Persona?> {
        return if (nombre.isNullOrEmpty()) {
            personaRepository!!.findAll()
        } else {
            personaRepository!!.findByNombre(nombre)
        }
    }
}