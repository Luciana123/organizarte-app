package com.fiuba.organizarteapp.controlador

import com.fiuba.organizarteapp.controlador.request.AgregarIntegranteRequest
import com.fiuba.organizarteapp.controlador.request.EspacioCreationRequest
import com.fiuba.organizarteapp.controlador.request.HogarCreationRequest
import com.fiuba.organizarteapp.dominio.Espacio
import com.fiuba.organizarteapp.repositorio.HogarEntity
import com.fiuba.organizarteapp.servicio.HogarServicio
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

import javax.websocket.server.PathParam

@RestController
@RequestMapping('hogar')
class HogarControlador {

    HogarServicio servicio

    @Autowired
    HogarControlador(HogarServicio servicio) {
        this.servicio = servicio
    }

    @GetMapping
    def getHogar(@PathParam Integer hogarId) {
        def h = servicio.getHogar(hogarId)
        def hogarDTO = new HogarEntity()
        hogarDTO.id = hogarId
        hogarDTO.nombre = h.nombre
    }

    @PostMapping
    def crear(@RequestBody HogarCreationRequest creacionHogar) {
        def hogar = this.servicio.crearHogar(creacionHogar.admin, creacionHogar.nombre)
        def hogarDTO = new HogarEntity()
        hogarDTO.id = hogar.id
        hogarDTO.nombre = hogar.nombre
        return hogarDTO
    }

    @PutMapping("/espacio")
    def agregarEspacio(@RequestBody EspacioCreationRequest nuevoEspacio) {
        Espacio e = new Espacio(nuevoEspacio.nombre, nuevoEspacio.cmCuadrados, nuevoEspacio.tipoEspacio)
        this.servicio.agregarEspacio(nuevoEspacio.hogarId, e)
        return e
    }

    @PutMapping("/integrante")
    def agregarIntegrante(@RequestBody AgregarIntegranteRequest nuevoIntegranteRequest) {
        this.servicio.agregarIntegrante(nuevoIntegranteRequest.hogarId, nuevoIntegranteRequest.integranteId)
    }

}
