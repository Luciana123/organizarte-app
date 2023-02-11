package com.fiuba.organizarteapp.repositorio

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table
import java.time.LocalDate

@Entity
@Table(name = 'tarea')
class TareaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id

    @Column(name = "espacio_id")
    Integer espacioId

    @Column(name = "integrante_id")
    Integer integranteId

    @Column(name = "nombre")
    String nombre

    @Column(name = "tipo_tarea_id")
    Integer tipoTareaId

    @Column(name = "realizada")
    boolean realizada

    @Column(name = "fecha_vencimiento")
    LocalDate fechaVencimiento


}
