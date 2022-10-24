package com.fiuba.organizarteapp.dominio

import spock.lang.Specification

import java.time.LocalDate

class HogarTest extends Specification {

    void "crear un hogar"() {
        given: "un administrador de hogar"
        def integrante = new Integrante(nombre: "Homero",
                fechaNacimiento: LocalDate.of(1998, 10, 22))
        when: "crea un hogar"
        def hogar = integrante.crearHogar("Familia Addams")
        then: "se crea el hogar exitosamente"
        assert hogar != null
        assert hogar.administradores.contains(integrante)
    }

}
