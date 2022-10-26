package com.fiuba.organizarteapp.dominio

import com.fiuba.organizarteapp.dominio.excepciones.OperacionNoPermitidaException
import com.fiuba.organizarteapp.dominio.excepciones.TareaInvalidaException

import java.time.LocalDate

class Tarea {

    String nombre
    LocalDate fechaVencimiento
    TipoTarea tipo
    Integer puntos
    Espacio espacio
    Estado estado
    Integrante asignado

    Tarea(String nombre, TipoTarea tipo, Espacio espacio, LocalDate hoy) {
        this.nombre = nombre
        this.fechaVencimiento = hoy.plusDays(tipo.periodicidad)

        if (tipo.tipoEspacio == null) {
            throw new TareaInvalidaException("""Se está intentando crear 
                    una tarea para un espacio, cuando la tarea 
                    no pertenece a ningún espacio.""")
        }

        if (tipo.tipoEspacio != espacio.tipo) {
            throw new TareaInvalidaException("""Se está intentando crear 
                    una tarea para un espacio incorrecto.""")
        }

        this.espacio = espacio
        this.estado = Estado.SinAsignar
        this.tipo = tipo
        this.puntos = this.calcularPuntosTarea(tipo.puntosBase)
    }

    Tarea(nombre, TipoTarea tipo, LocalDate hoy) {
        this.nombre = nombre
        this.fechaVencimiento = hoy.plusDays(tipo.periodicidad)
        this.tipo = tipo
        this.puntos = tipo.puntosBase
    }

    def actualizar(LocalDate today) {
        // Si la tarea venció la hago más pesada.
        if (this.fechaVencimiento.isBefore(today)  && this.estado != Estado.Realizada) {
            puntos = this.puntos + 1
            this.fechaVencimiento = today.plusDays(tipo.periodicidad)
        }

        // Si la tarea ya venció y está realizada, la finalizo
        if (this.fechaVencimiento.isBefore(today)  && this.estado == Estado.Realizada) {
            this.estado = Estado.Finalizada
        }
    }

    def marcarRealizada() {
        if (this.asignado != null) {
            this.estado = Estado.Realizada
        } else {
            throw new OperacionNoPermitidaException("La tara no tiene usuarios asignados para que puedan realizarla.")
        }
    }

    def anularRealizacion() {
        this.estado = Estado.Pendiente
    }

    def asignar(Integrante i) {
        this.asignado = i
    }

    private def calcularPuntosTarea(Integer puntosBase) {
        if (this.espacio != null && this.tipo.tipoEspacio != null) {
            var plus = this.espacio.getCmCuadrados() / 10
            return puntosBase + plus
        }
        return puntosBase
    }
}