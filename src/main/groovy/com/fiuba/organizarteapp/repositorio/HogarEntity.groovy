package com.fiuba.organizarteapp.repositorio

import javax.persistence.*

@Entity
@Table(name = 'hogar')
class HogarEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id

    @Column
    String nombre

}
