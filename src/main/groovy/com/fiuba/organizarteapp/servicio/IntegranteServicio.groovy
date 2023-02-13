package com.fiuba.organizarteapp.servicio


import com.fiuba.organizarteapp.dominio.Integrante
import com.fiuba.organizarteapp.repositorio.IntegranteEntity
import com.fiuba.organizarteapp.repositorio.IntegranteRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.CrossOrigin

@Service
class IntegranteServicio {

    IntegranteRepository repository

    @Autowired
    IntegranteServicio(IntegranteRepository repository) {
        this.repository = repository
    }

    def crearIntegrante(Integrante i) {
        def entity = new IntegranteEntity()
        entity.nombre = i.nombre
        repository.save(entity)
    }

    def obtener(Integer integranteId) {
        IntegranteEntity entity = this.repository.findById(integranteId)
                .orElseThrow(() -> new RuntimeException("not found"))
        Integrante i = new Integrante()
        i.nombre = entity.nombre
        i.id = entity.id
        return i
    }

    def obtener(String nombre) {
        List<IntegranteEntity> l = this.repository.getIntegranteByNombre(nombre)
        if (l.size() > 0) {
            def integrante = l.first()
            return integrante
        }
        throw new Exception("Not found")
    }


}
