package com.fiuba.organizarteapp.dominio

class TipoEspacio {
    String nombre
    List tareas = []

    def agregarPlantillaDeTarea(TipoTarea tarea) {
        tareas.add(tarea)
    }
}
