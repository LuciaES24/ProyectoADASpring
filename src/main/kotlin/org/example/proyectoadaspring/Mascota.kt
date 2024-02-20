package org.example.proyectoadaspring

import jakarta.persistence.*

@Entity
class Mascota {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var idMascota : Int? = null
    var nombreMascota : String? = null
    var tipo : String? = null
    @OneToMany(mappedBy = "idPrueba", cascade = [CascadeType.ALL])
    var pruebas : MutableList<Prueba>? = mutableListOf()
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dni")
    var dueno : Persona? = null
}