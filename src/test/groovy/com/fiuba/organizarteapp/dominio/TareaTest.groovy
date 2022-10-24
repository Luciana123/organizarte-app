package com.fiuba.organizarteapp.dominio

import com.fiuba.organizarteapp.dominio.excepciones.TareaInvalidaException
import spock.lang.Specification

import java.time.LocalDate

class TareaTest extends Specification {

    def setup() {

    }

    void "Creacion de tarea"() {
        given: "un tipo de tarea en un tipo de espacio"
        def tipoEspacio = new TipoEspacio(nombre: "Living")
        def tipoTareaTrapear = new TipoTarea("Trapear", "Pasar el trapo con desinfectante",
                7, 5, 5, tipoEspacio)

        when: "una instancia de tarea es creada"
        var livingPrincipal = new Espacio(nombre: "Living principal", cmCuadrados: 90, tipo: tipoEspacio)
        def tareaCreada = new Tarea("Trapear el living", tipoTareaTrapear, livingPrincipal, LocalDate.parse("2022-10-02"))

        then: "la tarea toma puntos de acuerdo al espacio"
        assert tareaCreada.puntos == 11
    }

    void "Creacion de tarea en un espacio incorrecto"() {
        given: "dos tipos de espacios distintos"
        def tipoEspacioLiving = new TipoEspacio(nombre: "Living")
        def tipoEspacioTaller = new TipoEspacio(nombre: "Taller")
        def tipoTareaTrapearLiving = new TipoTarea("Trapear living", "Pasar el trapo con desinfectante",
                7, 5, 5, tipoEspacioLiving)

        when: "al querer crear una tarea en un espacio incorrecto"
        var tallerPrincipal = new Espacio(nombre: "Living principal", cmCuadrados: 90, tipo: tipoEspacioTaller)
        def tareaCreada = new Tarea("Trapear el living", tipoTareaTrapearLiving, tallerPrincipal,
                LocalDate.parse("2022-10-02"))

        then: "se lanzará una excepción"
        def exception = thrown(TareaInvalidaException)
        exception != null
    }

    void "Creacion de tarea en un hogar sin espacio"() {
        given: "dos tipos de espacios distintos"

        def tipoTareaHacerCompras = new TipoTarea("Hacer compras", "Ir al supermercado a hacer las compras",
                7, 15, 70)

        when: "al crear una tarea"
        def tareaCreada = new Tarea("Hacer las compras de la casa - 1212",
                tipoTareaHacerCompras, LocalDate.parse("2022-10-02"))

        then: "la tarea se crea con los puntos base"
        assert tareaCreada.puntos == 15
    }



}
