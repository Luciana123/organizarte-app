package com.fiuba.organizarteapp.repositorio

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = 'integrante')
class IntegranteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id

    @Column(name = "hogar_id")
    Integer hogarId

    @Column(name = "es_admin")
    boolean esAdmin

    @Column(name = "nombre")
    String nombre

}
