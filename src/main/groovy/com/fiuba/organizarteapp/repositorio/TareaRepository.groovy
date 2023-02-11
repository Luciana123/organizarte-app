package com.fiuba.organizarteapp.repositorio

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TareaRepository extends JpaRepository<TareaEntity, Integer> {
    List<TareaEntity> getTareaByEspacioId(Integer espacioId);
}