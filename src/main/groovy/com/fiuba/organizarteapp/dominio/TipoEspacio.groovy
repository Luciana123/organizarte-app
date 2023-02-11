package com.fiuba.organizarteapp.dominio

class TipoEspacio {
    Integer id
    String nombre
    Set<TipoTarea> tipoTareas = []

    def agregarPlantillaDeTarea(TipoTarea tarea) {
        tipoTareas.add(tarea)
    }
}
