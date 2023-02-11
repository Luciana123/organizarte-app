package com.fiuba.organizarteapp.repositorio

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = 'tipo_tarea')
class TipoTareaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id

    @Column(name = "nombre")
    String nombre

    @Column(name = "tipo_espacio_id")
    Integer tipoEspacioId

    @Column(name = "periodicidad")
    Integer periodicidad

    @Column(name = "puntos")
    Integer puntos

}
