package org.example.proyectoadaspring.Service

import org.example.proyectoadaspring.Mascota
import org.example.proyectoadaspring.Prueba
import org.example.proyectoadaspring.Repository.MascotaRepository
import org.example.proyectoadaspring.Repository.PruebaRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * Servicio que maneja los datos de las pruebas, utilizando las funciones del repositorio
 *
 * @property mascotaRepository repositorio que determina las funciones de las mascotas
 * @property pruebaRepository repositorio que determina las funciones de las pruebas
 */
@Service
class PruebaService {
    @Autowired
    private val pruebaRepository : PruebaRepository? = null
    @Autowired
    private val mascotaRepository : MascotaRepository? = null

    /**
     * Pide los datos necesarios al controlador para añadir una prueba a la base de datos
     * @param nombrePrueba nombre de la prueba que vamos a registrar
     * @param fechaRealizada fecha de realización de la prueba que vamos a registrar
     * @param mascota id de la mascota de la prueba que vamos a registrar
     *
     * @return cadena con el resultado de la operación
     */
    fun addNewPrueba(
        nombrePrueba: String?, fechaRealizada : String?, mascota: String?
    ): String {
        var resultado: String
        try {
            if (nombrePrueba == "" || fechaRealizada == "" || mascota == ""){
                throw Exception()
            }else{
                val prueba = Prueba()
                val mascotaFind = getMascotaById(mascota!!.toInt())
                prueba.nombrePrueba = nombrePrueba
                prueba.fechaRealizada = fechaRealizada
                prueba.mascota = mascotaFind
                pruebaRepository!!.save(prueba)
                resultado = "Datos guardados correctamente"
            }
        }catch (e:Exception){
            resultado = "No se ha podido registrar la prueba"
        }
        return resultado
    }

    /**
     * Pide los datos necesarios al controlador para actualizar una prueba de la base de datos
     * @param idPrueba id de la prueba a la que vamos a modificar los datos
     * @param nombrePrueba nuevo nombre de la prueba que vamos a registrar
     * @param fechaRealizada nueva fecha de realización de la prueba que vamos a registrar
     * @param mascota nuevo id de la mascota de la prueba que vamos a registrar
     *
     * @return cadena con el resultado de la operación
     */
    fun updatePrueba(idPrueba: String?, nombrePrueba: String?, fechaRealizada : String?, mascota: String?): String {
        var resultado: String
        try {
            val prueba = getPruebaById(idPrueba!!.toInt())
            if (prueba == null || idPrueba == "" || nombrePrueba == "" || fechaRealizada == "" || mascota == ""){
                throw Exception()
            }else{
                prueba.nombrePrueba = nombrePrueba
                val mascotaFind = getMascotaById(mascota!!.toInt())
                prueba.fechaRealizada = fechaRealizada
                prueba.mascota = mascotaFind
                pruebaRepository!!.save(prueba)
                resultado = "Actualizado"
            }
        }catch (e:Exception){
            resultado = "No se ha encontrado la prueba"
        }
        return resultado
    }

    /**
     * Pide el id al controlador para eliminar una prueba de la base de datos
     * @param idPrueba id de la prueba que vamos a eliminar de la base de datos
     *
     * @return cadena con el resultado de la operación
     */
    fun delPrueba(idPrueba: String?): String {
        var resultado: String
        try {
            val prueba = getPruebaById(idPrueba!!.toInt())
            if (prueba == null || idPrueba == ""){
                throw Exception()
            }else{
                pruebaRepository!!.delete(prueba)
                resultado = "Eliminado"
            }
        }catch (e:Exception){
            resultado = "No se ha podido eliminar la prueba"
        }
        return resultado
    }

    /**
     * Busca a todas las pruebas de la base de datos
     *
     * @return cadena con el resultado de la operación
     */
    fun getPruebas(): MutableIterable<Prueba?> {
        return pruebaRepository!!.findAll()
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
     * Busca a todas las pruebas de la base de datos por nombre
     * @param nombrePrueba nombre de las pruebas que queremos buscar
     *
     * @return cadena con el resultado de la operación
     */
    fun getPruebaByNombrePrueba(nombrePrueba: String?): Iterable<Prueba?>{
        return if (nombrePrueba == null) {
            pruebaRepository!!.findAll()
        } else {
            pruebaRepository!!.findBynombrePrueba(nombrePrueba)
        }
    }

    /**
     * Busca a todas las pruebas de la base de datos por id de la mascota
     * @param mascota id de la mascota de las pruebas que queremos buscar
     *
     * @return cadena con el resultado de la operación
     */
    fun getPruebaByMascota(mascota: String?): Iterable<Prueba?>{
        return if (mascota == null) {
            pruebaRepository!!.findAll()
        } else {
            val mascotaFind = getMascotaById(mascota.toInt())
            pruebaRepository!!.findByMascota(mascotaFind!!)
        }
    }

    /**
     * Busca a todas las pruebas de la base de datos por fecha de realización
     * @param fecha fecha de realización de las pruebas que queremos buscar
     *
     * @return cadena con el resultado de la operación
     */
    fun getPruebaByFecha(fecha: String?): Iterable<Prueba?>{
        return if (fecha == null) {
            pruebaRepository!!.findAll()
        } else {
            pruebaRepository!!.findByFechaRealizada(fecha)
        }
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

}