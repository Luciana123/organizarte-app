package com.fiuba.organizarteapp.dominio

class Competencia {

    List<Tarea> tareas = []
    List<Integrante> integrantes = []

    EstadoCompetencia estadoCompetencia

    void empezarCompetencia(Integrante[] integrantes, Tarea[] tareas) {
        this.tareas = tareas
        this.integrantes = integrantes
        this.estadoCompetencia = EstadoCompetencia.EnCurso
    }

}