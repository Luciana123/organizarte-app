package com.fiuba.organizarteapp.servicio

import com.fiuba.organizarteapp.dominio.Tarea
import com.fiuba.organizarteapp.repositorio.HogarRepository
import com.fiuba.organizarteapp.repositorio.TareaRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import java.time.LocalDate

@Service
class TareaServicio {

    private HogarServicio hogarServicio
    private HogarRepository hogarRepository
    private TareaRepository tareaRepository

    @Autowired
    TareaServicio(HogarServicio hogarServicio, TareaRepository tareaRepository){
        this.hogarServicio = hogarServicio
        this.tareaRepository = tareaRepository
    }

    def getTareas(int integranteId){
        return tareaRepository.getTareasByIntegranteId(integranteId)
    }

    def repartir(int hogarId, int integranteId) {
        def h = this.hogarServicio.getHogar(hogarId)
        def integrante = h.getIntegrantes().find {it.id == integranteId}
        if (integrante == null) {
            throw new Exception("El integrante no pertenece al hogar.")
        }
        integrante.repartirTareas(LocalDate.now())
        hogarServicio.persistir(h)
    }

    def realizarTarea(int hogarId, int integranteId, int tareaId) {
        def myHogar = this.hogarServicio.getHogar(hogarId)

        def espacio = myHogar.espacios.find{
            it.tareas.any{it.id == tareaId}
        }

        def tarea = espacio.tareas.find{it.id == tareaId}

        def integrante = myHogar.integrantes.find{it.id == integranteId}
        integrante.realizarTarea(tarea)
        hogarServicio.persistir(myHogar)
        return "Done"
    }

}
