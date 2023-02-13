package com.fiuba.organizarteapp.controlador

import com.fiuba.organizarteapp.dominio.Integrante
import com.fiuba.organizarteapp.servicio.IntegranteServicio
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

import javax.websocket.server.PathParam

@RestController
@RequestMapping('integrante')
class IntegranteControlador {

    IntegranteServicio servicio

    @Autowired
    IntegranteControlador(IntegranteServicio servicio) {
        this.servicio = servicio
    }

    @PostMapping
    crear(@RequestBody Integrante integrante) {
        this.servicio.crearIntegrante(integrante)
    }

    @GetMapping
    get(@PathParam Integer integranteId) {
        this.servicio.obtener(integranteId)
    }

    @GetMapping("/{nombre}")
    getPorNombre(@PathVariable String nombre){
        this.servicio.obtener(nombre)
    }


}
