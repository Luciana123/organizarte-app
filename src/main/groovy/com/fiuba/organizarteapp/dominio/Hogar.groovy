package com.fiuba.organizarteapp.dominio

class Hogar {

    String nombre
    List integrantes = []
    Set administradores = []
    Set espacios = []
    Set tareas = []

    Organizador organizador

    Hogar(Integrante administrador, String nombre) {
        this.nombre = nombre
        // Primer integrante de la casa es el administrador.
        this.administradores.add(administrador)
        this.integrantes.add(administrador)
        this.organizador = new Organizador()
    }

    def agregarTarea(Tarea t) {
        this.tareas.add(t)
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

}
