package com.fiuba.organizarteapp.dominio

class TipoTarea {

    String nombre
    String descripcion
    Integer periodicidad
    Integer puntosBase
    Integer tiempoEstimadoMinutos
    TipoEspacio tipoEspacio

    TipoTarea(String nombre, String desc, Integer periodicidadDias,
              Integer puntos, Integer tiempoEstimadoMinutos,
              TipoEspacio espacio) {
        this.descripcion = desc
        this.nombre = nombre
        this.periodicidad = periodicidadDias
        this.puntosBase = puntos
        this.tiempoEstimadoMinutos = tiempoEstimadoMinutos
        this.tipoEspacio = espacio
        espacio.agregarPlantillaDeTarea(this)
    }

    TipoTarea(String nombre, String desc, Integer periodicidadDias,
              Integer puntos, Integer tiempoEstimadoMinutos) {
        this.descripcion = desc
        this.nombre = nombre
        this.periodicidad = periodicidadDias
        this.puntosBase = puntos
        this.tiempoEstimadoMinutos = tiempoEstimadoMinutos
    }

}
