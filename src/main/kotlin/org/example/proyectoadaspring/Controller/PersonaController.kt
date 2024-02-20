package org.example.proyectoadaspring.Controller

import org.example.proyectoadaspring.Persona
import org.example.proyectoadaspring.Service.PersonaService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

/**
 * Controlador que maneja los datos de las personas, comunicándose con el servicio
 *
 * Determina el mapeo para realizar las consultas y operaciones CRUD
 * @property personaService servicio de persona donde podremos realizar todas las operaciones con la base de datos
 */
@Controller
@RequestMapping(path=["/persona"])
class PersonaController {
    @Autowired
    private val personaService : PersonaService? = null

    /**
     * Pide los datos necesarios al usuario para añadir una persona a la base de datos
     * @param dni dni de la persona que vamos a registrar
     * @param nombre nombre de la persona que vamos a registrar
     * @param telefono teléfono de la persona que vamos a registrar
     * @param direccion dirección de la persona que vamos a registrar
     *
     * @return resultado de la funcion del servicio
     */
    @PostMapping(path=["/add"])
    @ResponseBody
    fun addNewPersona(
        @RequestParam dni: String?, @RequestParam nombre: String?, @RequestParam telefono: String?, @RequestParam direccion: String?
    ): String {
        return personaService!!.addNewPersona(dni, nombre, telefono, direccion)
    }

    /**
     * Pide los datos necesarios al usuario para actualizar una persona de la base de datos
     * @param dni dni de la persona a la que vamos a modificar los datos
     * @param nombre nuevo nombre de la persona que vamos a actualizar
     * @param telefono nuevo teléfono de la persona que vamos a actualizar
     * @param direccion nueva dirección de la persona que vamos a actualizar
     *
     * @return resultado de la funcion del servicio
     */
    @GetMapping(path = ["/update"])
    @ResponseBody
    fun updatePersona(
        @RequestParam dni: String?, @RequestParam nombre: String?, @RequestParam direccion: String?, @RequestParam telefono:String?): String {
        return personaService!!.updatePerson(dni, nombre, direccion, telefono)
    }

    /**
     * Pide el dni al usuario para eliminar a una persona de la base de datos
     * @param dni dni de la persona que vamos a eliminar de la base de datos
     *
     * @return resultado de la funcion del servicio
     */
    @GetMapping(path = ["/del"])
    @ResponseBody
    fun delPersona(
        @RequestParam dni: String?): String {
        return personaService!!.delPerson(dni)
    }

    /**
     * Busca a todas las personas de la base de datos
     *
     * @return resultado de la funcion del servicio
     */
    @GetMapping(path = ["/all"])
    @ResponseBody
    fun findPersonas(): MutableIterable<Persona?> {
        return personaService!!.getPersonas()
    }

    /**
     * Busca a todas las personas de la base de datos a partir de un nombre
     * @param nombre nombre de las personas que queremos buscar
     *
     * @return resultado de la funcion del servicio
     */
    @GetMapping(path = ["/nombre"])
    @ResponseBody
    fun findByNombrePersona(
        @RequestParam nombre: String?): Iterable<Persona?> {
        return personaService!!.getPersonaByNombre(nombre)
    }

    /**
     * Busca a una persona de la base de datos a partir del dni
     * @param dni dni de la persona que queremos buscar
     *
     * @return resultado de la funcion del servicio
     */
    @GetMapping(path = ["/dni"])
    @ResponseBody
    fun findByDniPersona(
        @RequestParam dni: String?): Persona? {
        return personaService!!.getPersonaByDni(dni)
    }

    /**
     * Busca a todas las personas de la base de datos a partir de una dirección
     * @param direccion dirección de la persona que queremos buscar
     *
     * @return resultado de la funcion del servicio
     */
    @GetMapping(path = ["/direccion"])
    @ResponseBody
    fun findByDireccionPersona(
        @RequestParam direccion: String?): Persona? {
        return personaService!!.getPersonaByDireccion(direccion)
    }

    /**
     * Busca una persona de la base de datos a partir de un teléfono
     * @param telefono teléfono de la persona que queremos buscar
     *
     * @return resultado de la funcion del servicio
     */
    @GetMapping(path = ["/telefono"])
    @ResponseBody
    fun findByTelefonoPersona(
        @RequestParam telefono: String?): Persona? {
        return personaService!!.getPersonaByTelefono(telefono)
    }
}