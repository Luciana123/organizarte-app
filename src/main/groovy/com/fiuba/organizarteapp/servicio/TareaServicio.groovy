package com.fiuba.organizarteapp.servicio

import com.fiuba.organizarteapp.repositorio.HogarRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import java.time.LocalDate

@Service
class TareaServicio {

    private HogarServicio hogarServicio
    private HogarRepository hogarRepository

    @Autowired
    TareaServicio(HogarServicio hogarServicio){
        this.hogarServicio = hogarServicio
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

}
