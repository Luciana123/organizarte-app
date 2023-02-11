package com.fiuba.organizarteapp.repositorio

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface EspacioRepository extends JpaRepository<EspacioEntity, Integer> {
    List<EspacioEntity> getEspacioByHogarId(Integer hogarId);
}