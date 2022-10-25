package com.fiuba.organizarteapp.dominio

import java.time.LocalDate

class Organizador {

    // Job encargado de avanzar el día cuando son las 00:00 para actualizar las tareas en el hogar.
    def avanzarDia(Hogar h, LocalDate hoy){
        // Se actualizan todas las tareas a las 00:00 hs de cada dia.

        // Actualizo tareas
        h.tareas.each {
            it.actualizar(hoy)
        }

        // Genero una nueva tarea si la tarea está realizada
        // con nueva fecha de vencimiento.
        // La asigno al siguiente participante.
        List<Tarea> nuevasTareas = []
        h.tareas.findAll{
            it.estado == Estado.Finalizada
        }.each {
            Tarea t = new Tarea(it.nombre, it.tipo, it.espacio, hoy)
            t.asignar(h.siguienteIntegrante(it.asignado))
            nuevasTareas.add(new Tarea(it.nombre, it.tipo, it.espacio, hoy))
        }

        // Borro tareas finalizadas
        h.tareas.removeAll{it.fechaVencimiento > hoy && it.estado == Estado.Realizada}

    }

}
