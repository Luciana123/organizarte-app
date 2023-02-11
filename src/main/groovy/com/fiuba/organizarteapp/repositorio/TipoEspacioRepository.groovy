package com.fiuba.organizarteapp.repositorio

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TipoEspacioRepository extends JpaRepository<TipoEspacioEntity, Integer> {}