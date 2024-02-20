package org.example.proyectoadaspring.Controller

import org.example.proyectoadaspring.Prueba
import org.example.proyectoadaspring.Service.PruebaService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

/**
 * Controlador que maneja los datos de las pruebas, comunicándose con el servicio
 *
 * Determina el mapeo para realizar las consultas y operaciones CRUD
 * @property pruebaService servicio de prueba donde podremos realizar todas las operaciones con la base de datos
 */
@Controller
@RequestMapping(path=["/prueba"])
class PruebaController {
    @Autowired
    private val pruebaService : PruebaService? = null

    /**
     * Pide los datos necesarios al usuario para añadir una prueba a la base de datos
     * @param nombrePrueba nombre de la prueba que vamos a registrar
     * @param fechaRealizada fecha de realización de la prueba que vamos a registrar
     * @param mascota id de la mascota de la prueba que vamos a registrar
     *
     * @return resultado de la funcion del servicio
     */
    @PostMapping(path=["/add"])
    @ResponseBody
    fun addNewPrueba(
        @RequestParam nombrePrueba: String?, @RequestParam fechaRealizada: String?, @RequestParam mascota: String?
    ): String {
        return pruebaService!!.addNewPrueba(nombrePrueba, fechaRealizada, mascota)
    }

    /**
     * Pide los datos necesarios al usuario para actualizar una prueba de la base de datos
     * @param idPrueba id de la prueba a la que vamos a modificar los datos
     * @param nombrePrueba nuevo nombre de la prueba que vamos a registrar
     * @param fechaRealizada nueva fecha de realización de la prueba que vamos a registrar
     * @param mascota nuevo id de la mascota de la prueba que vamos a registrar
     *
     * @return resultado de la funcion del servicio
     */
    @GetMapping(path = ["/update"])
    @ResponseBody
    fun updatePrueba(
        @RequestParam idPrueba: String?, @RequestParam nombrePrueba: String?, @RequestParam fechaRealizada: String?, @RequestParam mascota:String?): String {
        return pruebaService!!.updatePrueba(idPrueba, nombrePrueba, fechaRealizada, mascota)
    }

    /**
     * Pide el id al usuario para eliminar una prueba de la base de datos
     * @param idPrueba id de la prueba que vamos a eliminar de la base de datos
     *
     * @return resultado de la funcion del servicio
     */
    @GetMapping(path = ["/del"])
    @ResponseBody
    fun delPrueba(
        @RequestParam idPrueba: String?): String {
        return pruebaService!!.delPrueba(idPrueba)
    }

    /**
     * Busca a todas las pruebas de la base de datos por nombre
     * @param nombrePrueba nombre de las pruebas que queremos buscar
     *
     * @return resultado de la funcion del servicio
     */
    @GetMapping(path = ["/nombre"])
    @ResponseBody
    fun findByNombrePrueba(
        @RequestParam nombrePrueba: String?): Iterable<Prueba?> {
        return pruebaService!!.getPruebaByNombrePrueba(nombrePrueba)
    }

    /**
     * Busca a todas las pruebas de la base de datos
     *
     * @return resultado de la funcion del servicio
     */
    @GetMapping(path = ["/all"])
    @ResponseBody
    fun findPruebas(): MutableIterable<Prueba?> {
        return pruebaService!!.getPruebas()
    }

    /**
     * Busca a todas las pruebas de la base de datos por id
     * @param idPrueba id de la prueba que queremos buscar
     *
     * @return resultado de la funcion del servicio
     */
    @GetMapping(path = ["/id"])
    @ResponseBody
    fun findByIdPrueba(
        @RequestParam idPrueba: String?): Prueba? {
        return pruebaService!!.getPruebaById(idPrueba!!.toInt())
    }

    /**
     * Busca a todas las pruebas de la base de datos por fecha de realización
     * @param fechaRealizada fecha de realización de las pruebas que queremos buscar
     *
     * @return resultado de la funcion del servicio
     */
    @GetMapping(path = ["/fecha"])
    @ResponseBody
    fun findByFechaPrueba(
        @RequestParam fechaRealizada: String?): Iterable<Prueba?> {
        return pruebaService!!.getPruebaByFecha(fechaRealizada)
    }

    /**
     * Busca a todas las pruebas de la base de datos por id de la mascota
     * @param mascota id de la mascota de las pruebas que queremos buscar
     *
     * @return resultado de la funcion del servicio
     */
    @GetMapping(path = ["/mascota"])
    @ResponseBody
    fun findByMascotaPrueba(
        @RequestParam mascota: String?): Iterable<Prueba?> {
        return pruebaService!!.getPruebaByMascota(mascota)
    }
}