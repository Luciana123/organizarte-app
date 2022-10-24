package com.fiuba.organizarteapp.dominio

import com.fiuba.organizarteapp.dominio.excepciones.OperacionNoPermitidaException
import com.fiuba.organizarteapp.dominio.excepciones.TareaInvalidaException

import java.time.LocalDate

class Integrante {

    String nombre
    LocalDate fechaNacimiento
    Hogar hogar

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

    def crearEspacio(String nombre, Integer cmCuadrados, List tipoTareas) {
        var tipoEspacio = new TipoEspacio(nombre, tipoTareas)
        return new Espacio(nombre, )

    }

}
