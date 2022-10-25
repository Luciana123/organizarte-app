package com.fiuba.organizarteapp.dominio

class TipoEspacio {
    String nombre
    Set<TipoTarea> tipoTareas = []

    def agregarPlantillaDeTarea(TipoTarea tarea) {
        tipoTareas.add(tarea)
    }
}
