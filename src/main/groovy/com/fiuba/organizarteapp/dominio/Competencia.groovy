package com.fiuba.organizarteapp.dominio

import java.time.LocalDate

class Competencia {

    Hogar hogar
    Integer duracionDias = 14
    LocalDate fechaFinalizacion

    EstadoCompetencia estadoCompetencia
    Integrante ganador

    void empezarCompetencia(Hogar h, LocalDate hoy) {
        this.hogar = h
        this.estadoCompetencia = EstadoCompetencia.EnCurso
        this.fechaFinalizacion = hoy.plusDays(duracionDias)
    }

    void actualizar(LocalDate hoy) {
        if (hoy.isAfter(fechaFinalizacion)) {
            this.estadoCompetencia = EstadoCompetencia.Finalizada
            ganador = ganador()
        }
    }

    Integrante ganador() {
        return hogar.integrantes.max { it.puntosCompetencia }
    }

}