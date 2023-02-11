package com.fiuba.organizarteapp.repositorio

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TipoTareaRepository extends JpaRepository<TipoTareaEntity, Integer> {
    List<TipoTareaEntity> getTipoTareaByTipoEspacioId(Integer hogarId);

}