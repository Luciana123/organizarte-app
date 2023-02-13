package com.fiuba.organizarteapp.dominio

import com.fiuba.organizarteapp.dominio.excepciones.TareaInvalidaException

import java.time.LocalDate

class Hogar {

    Integer id
    String nombre
    Set<Integrante> integrantes = []
    Set<Integrante> administradores = []
    Set<Espacio> espacios = []

    // No contemplo tareas de Hogar en el MVP.
    Set<Tarea> tareas = []
    Set<TipoTarea> tipoTareas = []

    Competencia competenciaEnCurso = null

    Hogar(Integrante administrador, String nombre) {
        this.nombre = nombre
        // Primer integrante de la casa es el administrador.
        this.administradores.add(administrador)
        this.integrantes.add(administrador)
    }

    def agregarTipoTarea(TipoTarea t) {

        if (t.tipoEspacio != null) {
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
        // Crear tareas SOLO crea las tareas que no fueron creadas con
        // anterioridad, es decir, las tareas nuevas.
        var tipoTareasCreadas = tareas.each { it.tipo }
        var tareasNuevas = []
        tipoTareas.findAll {
            !tipoTareasCreadas.contains(it)
        }.each {
            var nombreTareaDeHogar = this.nombre + "-" + it.nombre
            tareasNuevas.add(new Tarea(nombreTareaDeHogar, it, hoy))
        }
        tareas.addAll(tareasNuevas)
        return tareasNuevas
    }

    def siguienteIntegrante(Integrante i) {
        Integer idx = integrantes.sort().findIndexOf { it.id == i.id }
        idx = (idx + 1) % (integrantes.size())
        Integrante next = integrantes.sort()[idx]
        return next
    }

    def empezarCompetencia(LocalDate hoy) {
        competenciaEnCurso = new Competencia()
        competenciaEnCurso.empezarCompetencia(this, hoy)
        integrantes.each {
            it.empezarCompetencia()
        }
    }

    def finalizarCompetencia() {
        competenciaEnCurso = null
    }

    def contieneTarea(Tarea tarea) {
        return this.espacios.any{
            it.tareas.contains(tarea)
        } || this.tareas.contains(tarea)
    }

}
