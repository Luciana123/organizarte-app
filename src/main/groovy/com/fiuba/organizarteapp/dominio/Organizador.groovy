package com.fiuba.organizarteapp.dominio

import java.time.LocalDate

class Organizador {

    // Job encargado de avanzar el día cuando son las 00:00 para actualizar las tareas en el hogar.
    def avanzarDia(Hogar h, LocalDate hoy) {
        // Se actualizan todas las tareas a las 00:00 hs de cada dia.

        // Actualizo tareas
        h.tareas.each {
            it.actualizar(hoy)
        }

        // Genero una nueva tarea si la tarea está realizada
        // con nueva fecha de vencimiento.
        // La asigno al siguiente participante.
        List<Tarea> nuevasTareas = []
        h.tareas.findAll {
            it.estado == Estado.Finalizada
        }.each {
            it.asignado.sumarPuntos(it.puntos)
            Tarea t = it.espacio?new Tarea(it.nombre, it.tipo, it.espacio, hoy):new Tarea(it.nombre, it.tipo, hoy)
            t.asignar(h.siguienteIntegrante(it.asignado))
            nuevasTareas.add(t)
        }

        h.tareas.addAll(nuevasTareas)
        // Borro tareas finalizadas
        h.tareas.removeAll {it.estado == Estado.Finalizada }

        // Actualizo competencia de haber
        h.competenciaEnCurso.each { it.actualizar(hoy) }

    }

}
