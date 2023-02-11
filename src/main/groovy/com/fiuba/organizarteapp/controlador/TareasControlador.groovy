package com.fiuba.organizarteapp.controlador

import com.fiuba.organizarteapp.servicio.TareaServicio
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping('tareas')
class TareasControlador {

    TareaServicio tareaServicio

    @Autowired
    TareasControlador(TareaServicio tareaServicio) {
        this.tareaServicio = tareaServicio
    }

    @PostMapping("/reparto")
    def repartir(@RequestParam Integer integranteId, @RequestParam Integer hogarId) {
        this.tareaServicio.repartir(hogarId, integranteId)
        return "Tareas repartidas"
    }

}
