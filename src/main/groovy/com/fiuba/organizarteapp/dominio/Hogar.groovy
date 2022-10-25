package com.fiuba.organizarteapp.dominio

import com.fiuba.organizarteapp.dominio.excepciones.TareaInvalidaException

import java.time.LocalDate

class Hogar {

    String nombre
    List<Integrante> integrantes = []
    Set<Integrante> administradores = []
    Set<TipoTarea> tipoTareas = []
    Set<Espacio> espacios = []
    Set<Tarea> tareas = []

    Organizador organizador

    Hogar(Integrante administrador, String nombre) {
        this.nombre = nombre
        // Primer integrante de la casa es el administrador.
        this.administradores.add(administrador)
        this.integrantes.add(administrador)
        this.organizador = new Organizador()
    }

    def agregarTipoTarea(TipoTarea t) {

        if(t.tipoEspacio != null) {
            throw new TareaInvalidaException("No se puede agreagar un tipo de tarea " +
                    "al hogar que sea relativa a un espacio, las tareas" +
                    "del hogar solo pueden ser tareas sin tipos de espacios asignados.")
        }

        this.tipoTareas.add(t)
    }

    def agregarIntegrante(Integrante integrante) {
        this.integrantes.add(integrante)
    }

    def quitarIntegrante(Integrante integrante) {
        // Dejar las tareas del integrante sin asignar.
        this.integrantes.remove(integrante)
    }

    def agregarEspacio(Espacio espacio) {
        this.espacios.add(espacio)
    }

    def esAdmin(Integrante i) {
        return this.administradores.contains(i)
    }

    def crearTareasDeHogar(LocalDate hoy) {
        tipoTareas.each {
            var nombreTareaDeHogar = this.nombre + "-" + it.nombre
            tareas.add(new Tarea(nombreTareaDeHogar, it, hoy))
        }
    }

    def siguienteIntegrante(Integrante i) {
        Integer idx = integrantes.indexOf(i)
        Integrante next =  integrantes.get(idx + 1 % integrantes.size())
        return next
    }


}
