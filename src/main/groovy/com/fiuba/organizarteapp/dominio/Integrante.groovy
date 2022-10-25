package com.fiuba.organizarteapp.dominio

import com.fiuba.organizarteapp.dominio.excepciones.EspacioInvalidoException
import com.fiuba.organizarteapp.dominio.excepciones.OperacionNoPermitidaException

import java.time.LocalDate

class Integrante {

    String nombre
    LocalDate fechaNacimiento
    Hogar hogar

    Integer puntosCompetencia = 0
    boolean enCompetencia = false

    Integrante() {
        this.nombre = nombre
        this.fechaNacimiento = fechaNacimiento
    }

    def crearHogar(String nombre) {
        if (this.hogar == null) {
            return new Hogar(this, nombre)
        } else {
            throw new OperacionNoPermitidaException("No se puede crear un hogar si se pertenece a uno.")
        }
    }

    def unirseAHogar(Hogar h) {
        if (this.hogar == null) {
            this.hogar = h
        } else {
            throw new OperacionNoPermitidaException("Ya se es parte de un hogar.")
        }
    }

    def salirDeHogar() {
        if (this.hogar != null) {
            this.hogar = null
            this.hogar.quitarIntegrante(this)
        } else {
            throw new OperacionNoPermitidaException("Ya se es parte de un hogar.")
        }
    }

    def crearEspacio(String nombre, Integer cmCuadrados, TipoEspacio tipoEspacio) {
        if (cmCuadrados < 0) {
            throw new EspacioInvalidoException("Los cmCuadrados de un espacio no pueden ser menores a 0")
        }
        hogar.agregarEspacio(new Espacio(nombre, cmCuadrados, tipoEspacio))
    }

    def empezarCompetencia() {
        if (!enCompetencia) {
            enCompetencia = true
            puntosCompetencia = 0
        }
    }

    def sumarPuntos(Integer puntos) {
        if (enCompetencia) {
            puntosCompetencia += puntos
        }
    }

    def finalizarCompetencia() {
        enCompetencia = false
    }

}
