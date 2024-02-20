package org.example.proyectoadaspring

import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.OneToMany

@Entity
class Persona {
    @Id
    var dni : String? = null
    var nombre: String? = null
    var direccion : String? = null
    var telefono : Int? = null
    @OneToMany(mappedBy = "idMascota", cascade = [CascadeType.ALL])
    var mascotas : MutableList<Mascota>? = mutableListOf()
}