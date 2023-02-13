package com.fiuba.organizarteapp.servicio

import com.fiuba.organizarteapp.dominio.Espacio
import com.fiuba.organizarteapp.dominio.Hogar
import com.fiuba.organizarteapp.dominio.Integrante
import com.fiuba.organizarteapp.repositorio.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class HogarServicio {

    HogarRepository hogarRepository
    IntegranteRepository integranteRepository
    EspacioRepository espacioRepository
    TipoEspacioRepository tipoEspacioRepository
    TipoTareaRepository tipoTareaRepository
    TareaRepository tareaRepository

    @Autowired
    HogarServicio(HogarRepository hogarRepository,
                  IntegranteRepository integranteRepository,
                  EspacioRepository espacioRepository,
                  TipoEspacioRepository tipoEspacioRepository, TipoTareaRepository tipoTareaRepository,
                  TareaRepository tarea) {
        this.hogarRepository = hogarRepository
        this.integranteRepository = integranteRepository
        this.espacioRepository = espacioRepository
        this.tipoEspacioRepository = tipoEspacioRepository
        this.tipoTareaRepository = tipoTareaRepository
        this.tareaRepository = tarea
    }

    // para fines interacciones.
    def getHogar(Integer hogarId) {
        HogarConstructor.construirHogarDesdeBD(hogarId,
                hogarRepository,
                integranteRepository,
                espacioRepository,
                tipoEspacioRepository,
                tipoTareaRepository,
                tareaRepository)
    }

    def crearHogar(Integrante administrador, String nombre) {
        Hogar h = administrador.crearHogar(nombre)
        HogarEntity he = new HogarEntity()
        IntegranteEntity i = new IntegranteEntity()

        he.nombre = nombre
        def saved = this.hogarRepository.save(he)

        def integranteToUpdate = this.integranteRepository.getReferenceById(administrador.id)
        integranteToUpdate.esAdmin = true
        integranteToUpdate.hogarId = saved.id

        this.integranteRepository.save(integranteToUpdate)
        return saved
    }

    def agregarEspacio(Integer hogarId, Espacio e) {
        def eEntity = new EspacioEntity()
        eEntity.nombre = e.nombre
        eEntity.hogarId = hogarId
        eEntity.cmCuadrados = e.cmCuadrados
        eEntity.tipoEspacioId = e.getTipo().id
        this.espacioRepository.save(eEntity)
    }

    def agregarIntegrante(Integer hogarId, Integer integranteId) {
        def integranteE = integranteRepository.findById(integranteId)
                .orElseThrow(() -> new RuntimeException("not found"))

        if (integranteE.hogarId != null) {
            throw new RuntimeException("El integrante ya tiene un hogar asignado.")
        }

        def h = this.getHogar(hogarId)

        // me aseguro de que este integrante no esté entre los integrantes de la casa
        def  integranteIdx = h.integrantes.findLastIndexOf {it.id == integranteE.id }
        if (integranteIdx == -1) {
            def newIntegrante = new Integrante()
            newIntegrante.nombre = integranteE.nombre
            newIntegrante.id = integranteE.id
            newIntegrante.hogar = h
            h.agregarIntegrante(newIntegrante)
            HogarConstructor.persistirHogarBD(h, integranteRepository, espacioRepository, tareaRepository)
        } else {
            throw new RuntimeException("El integrante ya está contemplado en el hogar.")
        }
        integranteE
    }

    def persistir(Hogar h) {
        HogarConstructor.persistirHogarBD(h, integranteRepository, espacioRepository, tareaRepository)
    }

}
