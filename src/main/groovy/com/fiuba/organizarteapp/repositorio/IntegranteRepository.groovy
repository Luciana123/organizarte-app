package com.fiuba.organizarteapp.repositorio

import com.fiuba.organizarteapp.dominio.Integrante
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface IntegranteRepository extends JpaRepository<IntegranteEntity, Integer> {
    List<IntegranteEntity> getIntegranteByHogarId(Integer hogarId);
}