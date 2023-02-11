package com.fiuba.organizarteapp.servicio

import com.fiuba.organizarteapp.dominio.Hogar
import com.fiuba.organizarteapp.dominio.Integrante
import com.fiuba.organizarteapp.repositorio.*

class HogarConstructor {

    static def construirHogarDesdeBD(Integer hogarId, HogarRepository hogarRepository,
                              IntegranteRepository integranteRepository,
                              EspacioRepository espacioRepository,
                              TipoEspacioRepository tipoEspacioRepository,
                              TipoTareaRepository tipoTareaRepository,
                              TareaRepository tareaRepository) {

        def hogarDTO = hogarRepository.findById(hogarId)
                .orElseThrow(() -> new RuntimeException("not found"))
        def integrantes = integranteRepository.getIntegranteByHogarId(hogarId)

        def integranteAdmin = new Integrante()
        integrantes.find {
            it.esAdmin
        }.each {
            integranteAdmin.nombre = it.nombre
            integranteAdmin.id = it.id
        }

        // genero entidad de dominio
        def hogar = new Hogar(integranteAdmin, hogarDTO.nombre)
        integranteAdmin.hogar = hogar

        // agrego integrantes
        integrantes.findAll {
            !it.esAdmin
        }.each {
            def integ = new Integrante()
            integ.nombre = it.nombre
            integ.id = it.id
            hogar.agregarIntegrante(integ)
            integ.hogar = hogar
        }

        // Agrego espacios
        def espacios = espacioRepository.getEspacioByHogarId(hogarId)
        espacios.each {
            def esp = EspacioConstructor.construirEspacioDesdeBD(it.id,
                    espacioRepository, tipoEspacioRepository,
                    tipoTareaRepository, tareaRepository, hogar.integrantes)
            hogar.agregarEspacio(esp)
        }
        hogar.id = hogarId
        hogar
    }

    // persisto en db cualquier cambio que pudo haber habido en el estado del hogar.
    static def persistirHogarBD(Hogar h,
                         IntegranteRepository integranteRepository,
                         EspacioRepository espacioRepository,
                         TareaRepository tareaRepository){

        //persisto cambios en integrantes
        h.integrantes.each {
            def integranteEntity = new IntegranteEntity()
            integranteEntity.nombre = it.nombre
            integranteEntity.id = it.id
            integranteEntity.esAdmin = h.esAdmin(it)
            integranteEntity.hogarId = h.id
            integranteRepository.save(integranteEntity)
        }

        h.espacios.each {
            def espacioEntity = new EspacioEntity()
            espacioEntity.id = it.id
            espacioEntity.hogarId = h.id
            espacioEntity.nombre = it.nombre
            espacioEntity.tipoEspacioId = it.tipo.id
            espacioEntity.cmCuadrados = it.cmCuadrados
            espacioRepository.save(espacioEntity)

            it.tareas.each {
                def tareaEntity = new TareaEntity()
                tareaEntity.id = it.id
                tareaEntity.nombre = it.nombre
                tareaEntity.fechaVencimiento = it.fechaVencimiento
                tareaEntity.tipoTareaId = it.tipo.id
                tareaEntity.espacioId = it.espacio.id
                tareaEntity.integranteId = it.asignado.id
                tareaEntity.realizada = it.esTareaRealizada()
                tareaRepository.save(tareaEntity)
            }
        }
    }
}
