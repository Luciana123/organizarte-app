package com.fiuba.organizarteapp.dominio

class Competencia {

    List tareas = []
    List integrantes = []

    EstadoCompetencia estadoCompetencia

    void empezarCompetencia(Integrante[] integrantes, Tarea[] tareas) {
        this.tareas = tareas
        this.integrantes = integrantes
        this.estadoCompetencia = EstadoCompetencia.EnCurso
    }

}