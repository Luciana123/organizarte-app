package com.fiuba.organizarteapp.servicio

import com.fiuba.organizarteapp.dominio.Espacio
import com.fiuba.organizarteapp.dominio.Estado
import com.fiuba.organizarteapp.dominio.Integrante
import com.fiuba.organizarteapp.dominio.Tarea
import com.fiuba.organizarteapp.dominio.TipoEspacio
import com.fiuba.organizarteapp.dominio.TipoTarea
import com.fiuba.organizarteapp.repositorio.EspacioRepository
import com.fiuba.organizarteapp.repositorio.TareaRepository
import com.fiuba.organizarteapp.repositorio.TipoEspacioRepository
import com.fiuba.organizarteapp.repositorio.TipoTareaRepository

class EspacioConstructor {

    static def construirEspacioDesdeBD(Integer espacioId, EspacioRepository espacioRepository,
                                TipoEspacioRepository tipoEspacioRepository,
                                TipoTareaRepository tipoTareaRepository,
                                TareaRepository tareaRepository,
                                Set<Integrante> integrantes) {

        def espacio = espacioRepository.findById(espacioId)
                .orElseThrow(() -> new RuntimeException("not found"))

        def tipoEspacioDTO = tipoEspacioRepository.findById(espacio.tipoEspacioId)
                    .orElseThrow(() -> new RuntimeException("not found"))

        // encontrar tipoTareas del tipoEspacio:
        def tipoTareasDeTipoEspacio =
                tipoTareaRepository.getTipoTareaByTipoEspacioId(espacio.tipoEspacioId)

        def tipoEspacio = new TipoEspacio()
        tipoEspacio.nombre = tipoEspacioDTO.nombre
        tipoEspacio.id = tipoEspacioDTO.id

        List<TipoTarea> tiposTarea = []
        tipoTareasDeTipoEspacio.each {
            tiposTarea.add(new TipoTarea(it.id, it.nombre, it.periodicidad, it.puntos, tipoEspacio))
        }

        def e = new Espacio(espacio.nombre, espacio.cmCuadrados, tipoEspacio)
        e.id = espacioId

        //agrego tareas creadas
        def tareasDeEspacio = tareaRepository.getTareaByEspacioId(espacioId)

        tareasDeEspacio.each { te ->
            def tipoTarea = tiposTarea.find{it.id == te.tipoTareaId}
            def tarea = new Tarea()
            tarea.id = te.id
            tarea.estado = te.realizada? Estado.Realizada:te.integranteId?Estado.Pendiente:Estado.SinAsignar
            tarea.fechaVencimiento = te.fechaVencimiento
            tarea.nombre = te.nombre
            tarea.tipo = tipoTarea
            tarea.espacio = e

            integrantes.find { ie ->
                ie.id == te.id
            }.each { ie ->
                tarea.asignar(ie)
            }
        }

        return e
    }
}
