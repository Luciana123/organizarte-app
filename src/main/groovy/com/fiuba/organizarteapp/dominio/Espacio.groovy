package com.fiuba.organizarteapp.dominio

import java.time.LocalDate

class Espacio {

    Integer id
    String nombre
    Integer cmCuadrados
    TipoEspacio tipo

    List<Tarea> tareas = []

    Espacio(String nombre, Integer cmCuadrados, TipoEspacio tipoEspacio) {
        this.nombre = nombre
        this.cmCuadrados = cmCuadrados
        this.tipo = tipoEspacio
    }

    List<Tarea> crearTareas(Hogar h, LocalDate hoy) {
        // Crear tareas SOLO crea las tareas que no fueron creadas con
        // anterioridad, es decir, las tareas nuevas.
        var tipoTareasCreadas = tareas.each { it.tipo }
        var tareasNuevas = []
        tipo.tipoTareas.findAll {
            !tipoTareasCreadas.contains(it)
        }.each {
            var nombreTareaDeEspacio = h.nombre + "-" + this.nombre + "-" + it.nombre
            tareasNuevas.add(new Tarea(nombreTareaDeEspacio, it, this, hoy))
        }
        tareas.addAll(tareasNuevas)
        return tareasNuevas
    }

}
