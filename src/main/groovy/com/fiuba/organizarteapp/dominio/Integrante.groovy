package com.fiuba.organizarteapp.dominio

import com.fiuba.organizarteapp.dominio.excepciones.EspacioInvalidoException
import com.fiuba.organizarteapp.dominio.excepciones.OperacionNoPermitidaException

import java.time.LocalDate

class Integrante {

    Integer id
    String nombre
    LocalDate fechaNacimiento
    Hogar hogar

    Integer puntosCompetencia = 0
    boolean enCompetencia = false

    def crearHogar(String nombre) {
        if (this.hogar == null) {
            hogar = new Hogar(this, nombre)
            return hogar
        } else {
            throw new OperacionNoPermitidaException("No se puede crear un hogar si se pertenece a uno.")
        }
    }

    def unirseAHogar(Hogar h) {
        if (this.hogar == null) {
            this.hogar = h
            this.hogar.agregarIntegrante(this)
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

    def repartirTareas(LocalDate hoy){

        // todo verificar que haya espacios y/o tareas
        List<Tarea> tareasCreadas = []

        if (hogar.esAdmin(this)) {
            List<Tarea> tareasDeEspacios = hogar.espacios
                    .collectMany {return it.crearTareas(hogar, hoy)}
            tareasCreadas.addAll(tareasDeEspacios)
            tareasCreadas.addAll(hogar.crearTareasDeHogar(hoy))

            // Asigno tareas
            Integrante actual = this
            tareasCreadas.each {
                it.asignado = actual
                actual = hogar.siguienteIntegrante(actual)
            }
        }

        this.hogar.tareas.addAll(tareasCreadas)


    }

    def crearEspacio(String nombre, Integer cmCuadrados, TipoEspacio tipoEspacio) {
        if (cmCuadrados < 0) {
            throw new EspacioInvalidoException("Los cmCuadrados de un espacio no pueden ser menores a 0")
        }
        hogar.agregarEspacio(new Espacio(nombre, cmCuadrados, tipoEspacio))
    }

    def crearEspacio(Espacio e) {
        hogar.agregarEspacio(e)
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

    def realizarTarea(Tarea t) {
        if(t.asignado == this && hogar.tareas.contains(t)){
            t.marcarRealizada()
        }
    }

}
