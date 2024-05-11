package org.example.proyectoadaspring

import jakarta.persistence.*

@Entity
class Prueba {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var idPrueba : Int? = null
    var nombrePrueba : String? = null
    var fechaRealizada : String? = null
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idMascota")
    var mascota : Mascota? = null
}