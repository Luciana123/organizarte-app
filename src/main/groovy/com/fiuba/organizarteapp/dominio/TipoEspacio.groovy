package com.fiuba.organizarteapp.dominio

class TipoEspacio {
    String nombre
    List<TipoTarea> tipoTareas = []

    def agregarPlantillaDeTarea(TipoTarea tarea) {
        tipoTareas.add(tarea)
    }
}
