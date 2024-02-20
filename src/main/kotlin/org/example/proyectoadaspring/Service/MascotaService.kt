package org.example.proyectoadaspring.Service

import org.example.proyectoadaspring.Mascota
import org.example.proyectoadaspring.Persona
import org.example.proyectoadaspring.Prueba
import org.example.proyectoadaspring.Repository.MascotaRepository
import org.example.proyectoadaspring.Repository.PersonaRepository
import org.example.proyectoadaspring.Repository.PruebaRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * Servicio que maneja los datos de las mascotas, utilizando las funciones del repositorio
 *
 * @property mascotaRepository repositorio que determina las funciones de las mascotas
 * @property personaRepository repositorio que determina las funciones de las personas
 * @property pruebaRepository repositorio que determina las funciones de las pruebas
 */
@Service
class MascotaService {
    @Autowired
    private val mascotaRepository : MascotaRepository? = null
    @Autowired
    private val personaRepository : PersonaRepository? = null
    @Autowired
    private val pruebaRepository : PruebaRepository? = null

    /**
     * Pide los datos necesarios al controlador para añadir una mascota a la base de datos
     * @param nombreMascota nombre de la mascota que vamos a registrar
     * @param tipo tipo de mascota que vamos a registrar
     * @param dniDueno dni del dueño de la mascota que vamos a registrar
     *
     * @return cadena con el resultado de la operación
     */
    fun addNewMascota(
        nombreMascota: String?, tipo: String?, dniDueno:String?
    ): String {
        var resultado: String
        try {
            if (nombreMascota == "" || tipo == "" || dniDueno == ""){
                throw Exception()
            }else{
                val mascota = Mascota()
                val dueno = getPersonaByDni(dniDueno)
                mascota.nombreMascota = nombreMascota
                mascota.tipo = tipo
                mascota.dueno = dueno
                mascotaRepository!!.save(mascota)
                resultado = "Datos guardados correctamente"
            }
        }catch (e:Exception){
            resultado = "No se ha podido guardar"
        }
        return resultado
    }

    /**
     * Pide los datos necesarios al controlador para actualizar una mascota de la base de datos
     * @param idMascota id de la mascota a la que vamos a modificar los datos
     * @param nombreMascota nuevo nombre de la mascota que vamos a registrar
     * @param tipo nuevo tipo de mascota que vamos a registrar
     * @param dueno nuevo dni del dueño de la mascota que vamos a registrar
     *
     * @return cadena con el resultado de la operación
     */
    fun updateMascota(idMascota: String?, nombreMascota: String?, tipo: String?, dueno:String?): String {
        var resultado: String
        try {
            val mascota = getMascotaById(idMascota!!.toInt())
            if (mascota == null || idMascota == "" || nombreMascota == "" || tipo == "" || dueno == ""){
                throw Exception()
            }else{
                mascota.nombreMascota = nombreMascota
                mascota.tipo = tipo
                val duenoMascota = getPersonaByDni(dueno!!)
                mascota.dueno = duenoMascota
                mascotaRepository!!.save(mascota)
                resultado = "Actualizado"
            }
        }catch (e:Exception){
            resultado = "No se ha encontrado el animal"
        }
        return resultado
    }

    /**
     * Pide el id al controlador para eliminar a una mascota de la base de datos
     * @param idMascota id de la mascota que vamos a eliminar de la base de datos
     *
     * @return cadena con el resultado de la operación
     */
    fun delMascota(idMascota: String?): String {
        var resultado: String
        try {
            val mascota = getMascotaById(idMascota!!.toInt())
            if (mascota == null || idMascota == ""){
                throw Exception()
            }else{
                mascotaRepository!!.delete(mascota)
                for (prueba in mascota.pruebas!!){
                    val pruebaFind = getPruebaById(mascota.idMascota)
                    if (pruebaFind != null){
                        pruebaRepository!!.delete(pruebaFind)
                    }
                }
                resultado = "Eliminado"
            }
        }catch (e: Exception){
            resultado = "No se ha podido eliminar la mascota"
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
     * Busca a todas las mascotas de la base de datos por nombre
     * @param nombreMascota nombre de las mascotas que queremos buscar
     *
     * @return cadena con el resultado de la operación
     */
    fun getMascotaByNombre(nombreMascota: String?): Iterable<Mascota?>{
        return if (nombreMascota == null) {
            mascotaRepository!!.findAll()
        } else {
            mascotaRepository!!.findBynombreMascota(nombreMascota)
        }
    }

    /**
     * Busca a todas las mascotas de la base de datos
     *
     * @return cadena con el resultado de la operación
     */
    fun getMascotas(): MutableIterable<Mascota?> {
        return mascotaRepository!!.findAll()
    }

    /**
     * Busca a todas las mascotas de la base de datos por tipo
     * @param tipo tipo de las mascotas que queremos buscar
     *
     * @return cadena con el resultado de la operación
     */
    fun getMascotaByTipo(tipo: String?): Iterable<Mascota?>{
        return if (tipo == null) {
            mascotaRepository!!.findAll()
        } else {
            mascotaRepository!!.findByTipo(tipo)
        }
    }

    /**
     * Busca a todas las mascotas de la base de datos por dueno
     * @param dueno dni del dueño de las mascotas que queremos buscar
     *
     * @return cadena con el resultado de la operación
     */
    fun getMascotaByDueno(dueno: String?): Iterable<Mascota?>{
        return if (dueno == null) {
            mascotaRepository!!.findAll()
        } else {
            val duenoFind = getPersonaByDni(dueno)
            mascotaRepository!!.findByDueno(duenoFind!!)
        }
    }

    /**
     * Busca a todas las pruebas de la base de datos por id
     * @param id id de la prueba que queremos buscar
     *
     * @return cadena con el resultado de la operación
     */
    fun getPruebaById(id: Int?): Prueba?{
        return if (id == null) {
            Prueba()
        } else {
            pruebaRepository!!.findByidPrueba(id)
        }
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
}