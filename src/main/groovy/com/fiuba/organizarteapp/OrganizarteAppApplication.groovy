package com.fiuba.organizarteapp

import com.fiuba.organizarteapp.dominio.TipoEspacio
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class OrganizarteAppApplication {

    static void main(String[] args) {
        SpringApplication.run(OrganizarteAppApplication, args)

        // Crear TipoEspacios defaults:
        // Baño
        // Living
        // Cocina
        // Balcon
        // Cuarto

        List tipoEspacios = []
        var baño = new TipoEspacio(nombre: "Baño")
        var cocina = new TipoEspacio(nombre: "Cocina")
    }

}
