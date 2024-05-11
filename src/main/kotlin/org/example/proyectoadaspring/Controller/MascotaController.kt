package org.example.proyectoadaspring.Controller

import org.example.proyectoadaspring.Mascota
import org.example.proyectoadaspring.Service.MascotaService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody

/**
 * Controlador que maneja los datos de las mascotas, comunicándose con el servicio
 *
 * Determina el mapeo para realizar las consultas y operaciones CRUD
 * @property mascotaService servicio de mascota donde podremos realizar todas las operaciones con la base de datos
 */
@Controller
@RequestMapping(path=["/mascota"])
class MascotaController {
    @Autowired
    private val mascotaService : MascotaService? = null

    /**
     * Pide los datos necesarios al usuario para añadir una mascota a la base de datos
     * @param nombreMascota nombre de la mascota que vamos a registrar
     * @param tipo tipo de mascota que vamos a registrar
     * @param dniDueno dni del dueño de la mascota que vamos a registrar
     *
     * @return resultado de la funcion del servicio
     */
    @PostMapping(path=["/add"])
    @ResponseBody
    fun addNewMascota(
        @RequestParam nombreMascota: String?, @RequestParam tipo: String?, @RequestParam dniDueno:String?): String {
        return mascotaService!!.addNewMascota(nombreMascota,tipo,dniDueno)
    }

    /**
     * Pide los datos necesarios al usuario para actualizar una mascota de la base de datos
     * @param idMascota id de la mascota a la que vamos a modificar los datos
     * @param nombreMascota nuevo nombre de la mascota que vamos a registrar
     * @param tipo nuevo tipo de mascota que vamos a registrar
     * @param dueno nuevo dni del dueño de la mascota que vamos a registrar
     *
     * @return resultado de la funcion del servicio
     */
    @GetMapping(path = ["/update"])
    @ResponseBody
    fun updateMascota(
        @RequestParam idMascota: String?, @RequestParam nombreMascota: String?, @RequestParam tipo: String?, @RequestParam dueno:String?): String {
        return mascotaService!!.updateMascota(idMascota, nombreMascota, tipo, dueno)
    }

    /**
     * Pide el id al usuario para eliminar a una mascota de la base de datos
     * @param idMascota id de la mascota que vamos a eliminar de la base de datos
     *
     * @return resultado de la funcion del servicio
     */
    @GetMapping(path = ["/del"])
    @ResponseBody
    fun delMascota(
        @RequestParam idMascota: String?): String {
        return mascotaService!!.delMascota(idMascota)
    }

    /**
     * Busca a todas las mascotas de la base de datos por nombre
     * @param nombreMascota nombre de las mascotas que queremos buscar
     *
     * @return resultado de la funcion del servicio
     */
    @GetMapping(path = ["/nombre"])
    @ResponseBody
    fun findByNombre(
        @RequestParam nombreMascota: String?): Iterable<Mascota?> {
        return mascotaService!!.getMascotaByNombre(nombreMascota)
    }

    /**
     * Busca a la mascota de la base de datos por id
     * @param idMascota id de la mascota que queremos buscar
     *
     * @return resultado de la funcion del servicio
     */
    @GetMapping(path = ["/id"])
    @ResponseBody
    fun findByIdMascota(
        @RequestParam idMascota: String?): Mascota? {
        return mascotaService!!.getMascotaById(idMascota!!.toInt())
    }

    /**
     * Busca a todas las mascotas de la base de datos
     *
     * @return resultado de la funcion del servicio
     */
    @GetMapping(path = ["/all"])
    @ResponseBody
    fun findMascotas(): MutableIterable<Mascota?> {
        return mascotaService!!.getMascotas()
    }

    /**
     * Busca a todas las mascotas de la base de datos por tipo
     * @param tipo tipo de las mascotas que queremos buscar
     *
     * @return resultado de la funcion del servicio
     */
    @GetMapping(path = ["/tipo"])
    @ResponseBody
    fun findByTipoMascota(
        @RequestParam tipo: String?): Iterable<Mascota?> {
        return mascotaService!!.getMascotaByTipo(tipo)
    }

    /**
     * Busca a todas las mascotas de la base de datos por dueno
     * @param dueno dni del dueño de las mascotas que queremos buscar
     *
     * @return resultado de la funcion del servicio
     */
    @GetMapping(path = ["/dueno"])
    @ResponseBody
    fun findByDuenoMascota(
        @RequestParam dueno: String?): Iterable<Mascota?> {
        return mascotaService!!.getMascotaByDueno(dueno)
    }
}