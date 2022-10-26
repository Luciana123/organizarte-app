package com.fiuba.organizarteapp.dominio

import spock.lang.Specification

import java.time.LocalDate

class IntegranteTest extends Specification {

    LocalDate hoy

    Espacio piezaMerlina
    Espacio piezaPericles
    Espacio cocina

    def setup() {
        hoy = LocalDate.parse("2022-10-01")
        def tipoEspacioPieza = new TipoEspacio(nombre: "Pieza")
        def tipoEspacioCocina = new TipoEspacio(nombre: "Cocina")

        new TipoTarea("Barrer", "Barrer la pieza", 2, 2, 5, tipoEspacioPieza)
        new TipoTarea("Ordenar", "Ordenar la pieza", 2, 2, 5, tipoEspacioPieza)

        new TipoTarea("Barrer", "Barrer la cocina", 2, 2, 5, tipoEspacioCocina)
        new TipoTarea("Limpiar heladera", "Ordenar la pieza", 50, 20, 50, tipoEspacioCocina)

        piezaMerlina = new Espacio("Pieza principal", 65, tipoEspacioPieza)
        piezaPericles = new Espacio("Pieza principal", 80, tipoEspacioPieza)
        cocina = new Espacio("Cocina familiar", 50, tipoEspacioCocina)

    }

    void "crear un hogar"() {
        given: "que soy un integrante"
        def integrante = new Integrante(nombre: "Homero",
                fechaNacimiento: LocalDate.of(1998, 10, 22))
        when: "creo un hogar"
        def hogar = integrante.crearHogar("Familia Addams")
        then: "soy el administrador"
        assert hogar.administradores.contains(integrante)
    }

    void "unirme a un hogar"() {
        given: "que soy un integrante"
        def integrante = new Integrante(nombre: "Homero",
                fechaNacimiento: LocalDate.of(1998, 10, 22))
        def integrante2 = new Integrante(nombre: "Merlina",
                fechaNacimiento: LocalDate.of(2000, 9, 14))
        def hogar = integrante.crearHogar("Familia Addams")
        when: "me uno a un hogar"
        integrante2.unirseAHogar(hogar)
        then: "soy un integrante del hogar"
        assert hogar.integrantes.contains(integrante2)
        assert hogar.integrantes.contains(integrante)
    }

    void "repartir tareas"() {
        given: "que hay 4 integrantes en el hogar"
        def integrante = new Integrante(nombre: "Homero",
                fechaNacimiento: LocalDate.of(1970, 10, 22))
        def integrante2 = new Integrante(nombre: "Merlina",
                fechaNacimiento: LocalDate.of(1998, 9, 14))
        def integrante3 = new Integrante(nombre: "Morticia",
                fechaNacimiento: LocalDate.of(1974, 10, 22))
        def integrante4 = new Integrante(nombre: "Pericles",
                fechaNacimiento: LocalDate.of(2000, 9, 14))
        def hogar = integrante.crearHogar("Familia Addams")
        integrante.crearEspacio(piezaMerlina)
        integrante.crearEspacio(piezaPericles)
        integrante.crearEspacio(cocina)
        integrante2.unirseAHogar(hogar)
        integrante3.unirseAHogar(hogar)
        integrante4.unirseAHogar(hogar)
        when: "reparto las tareas"
        integrante.repartirTareas(hoy)
        then: "los integrantes reciben tareas"
        hogar.tareas.collect{
            assert it.asignado != null
        }
    }
}
