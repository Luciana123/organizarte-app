package com.fiuba.organizarteapp.dominio

import spock.lang.Specification

import java.time.LocalDate

class OrganizadorTest extends Specification {

    LocalDate hoy
    Hogar hogar

    def setup() {
        hoy = LocalDate.parse("2022-10-01")
        def integrante = new Integrante(nombre: "Homero",
                fechaNacimiento: LocalDate.of(1970, 10, 22))
        def integrante2 = new Integrante(nombre: "Merlina",
                fechaNacimiento: LocalDate.of(1998, 9, 14))

        hogar = integrante.crearHogar("Familia Addams")
        integrante2.unirseAHogar(hogar)
    }


    void "avanzar un dia: tarea no realizada"() {
        given: "tengo una tarea que vence en dos dias"
        def t = new TipoTarea("tipo test", "test", 2, 12, 20)
        def tarea = new Tarea("tarea test", t, hoy)
        hogar.tareas.add(tarea)
        when: "pasan tres días y la tarea no se realiza"
        new Organizador().avanzarDia(hogar, hoy.plusDays(1))
        new Organizador().avanzarDia(hogar, hoy.plusDays(2))
        new Organizador().avanzarDia(hogar, hoy.plusDays(3))
        then: "la tarea se vence y se le agrega un punto"
        assert tarea.puntos == 13
    }

    void "avanzar un dia: tarea realizada"() {
        given: "tengo una tarea que vence en dos dias"
        def t = new TipoTarea("tipo test", "test", 2, 12, 20)
        def tarea = new Tarea("tarea test", t, hoy)
        hogar.tareas.add(tarea)
        when: "pasan tres días y la tarea se realiza"
        new Organizador().avanzarDia(hogar, hoy.plusDays(1))
        tarea.asignado = new Integrante(nombre: "Casandra")
        tarea.marcarRealizada()
        new Organizador().avanzarDia(hogar, hoy.plusDays(2))
        new Organizador().avanzarDia(hogar, hoy.plusDays(3))
        then: "la tarea desaparece de la lista de tareas y se crea una nueva del mismo tipo"
        assert !hogar.tareas.contains(tarea)
        assert hogar.tareas.first().tipo.nombre == "tipo test"
        assert hogar.tareas.size() == 1
    }
}