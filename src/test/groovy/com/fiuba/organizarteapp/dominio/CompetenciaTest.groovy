package com.fiuba.organizarteapp.dominio

import spock.lang.Specification

import java.time.LocalDate

class CompetenciaTest extends Specification {

    LocalDate hoy
    Organizador organizador = new Organizador()
    Hogar Hogar
    Integrante homero
    Integrante casandra
    Integrante elvira

    def setup() {
        hoy = LocalDate.parse("2022-10-01")
        def tipoEspacioPieza = new TipoEspacio(nombre: "Pieza")
        def tipoEspacioCocina = new TipoEspacio(nombre: "Cocina")

        new TipoTarea("Barrer", "Barrer la pieza", 2, 2, 5, tipoEspacioPieza)
        new TipoTarea("Ordenar", "Ordenar la pieza", 2, 2, 5, tipoEspacioPieza)

        new TipoTarea("Barrer", "Barrer la cocina", 2, 2, 5, tipoEspacioCocina)
        new TipoTarea("Limpiar heladera", "Ordenar la pieza", 50, 20, 50, tipoEspacioCocina)

        def piezaMerlina = new Espacio("Pieza principal", 65, tipoEspacioPieza)
        def piezaPericles = new Espacio("Pieza principal", 80, tipoEspacioPieza)
        def cocina = new Espacio("Cocina familiar", 50, tipoEspacioCocina)

        homero = new Integrante(nombre: "Homero",
                fechaNacimiento: LocalDate.of(1970, 10, 22))
        casandra = new Integrante(nombre: "Casandra",
                fechaNacimiento: LocalDate.of(1998, 9, 14))
        elvira = new Integrante(nombre: "Elvira",
                fechaNacimiento: LocalDate.of(1974, 10, 22))

        hogar = homero.crearHogar("Familia Addams")
        homero.crearEspacio(piezaMerlina)
        homero.crearEspacio(piezaPericles)
        homero.crearEspacio(cocina)
        elvira.unirseAHogar(hogar)
        casandra.unirseAHogar(hogar)

        homero.repartirTareas(hoy)

    }

    void "Ganar competencia"() {
        given: "se empieza una competencia en un hogar"
        hogar.empezarCompetencia(hoy)
        when: "termina la competencia"
        elvira.realizarTarea(hogar.tareas.find {it.asignado == elvira})
        pasanDias(15)
        then: "gana el participante con más cantidad de puntos"
        assert hogar.competenciaEnCurso.ganador() == elvira
        assert hogar.competenciaEnCurso.estadoCompetencia == EstadoCompetencia.Finalizada
    }

    void "Competencia en curso"() {
        given: "se empieza una competencia en un hogar"
        hogar.empezarCompetencia(hoy)
        when: "pasan 5 días"
        elvira.realizarTarea(hogar.tareas.find {it.asignado == elvira})
        pasanDias(5)
        then: "la competencia sigue en curso"
        assert hogar.competenciaEnCurso.estadoCompetencia == EstadoCompetencia.EnCurso
    }

    def pasanDias(int cantidad) {
        1..cantidad.each {
            organizador.avanzarDia(hogar, hoy.plusDays(it))
        }

    }

}
