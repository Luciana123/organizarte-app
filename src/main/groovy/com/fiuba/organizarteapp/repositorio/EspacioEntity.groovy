package com.fiuba.organizarteapp.repositorio

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = 'espacio')
class EspacioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id

    @Column(name = "hogar_id", nullable = false)
    Integer hogarId

    @Column(name = "nombre", nullable = false)
    String nombre

    @Column(name = "tipo_espacio_id", nullable = false)
    Integer tipoEspacioId

    @Column(name = "cm_cuadrados", nullable = false)
    Integer cmCuadrados

}
