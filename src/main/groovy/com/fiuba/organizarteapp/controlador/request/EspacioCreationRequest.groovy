package com.fiuba.organizarteapp.controlador.request

import com.fiuba.organizarteapp.dominio.TipoEspacio

class EspacioCreationRequest {
    TipoEspacio tipoEspacio
    String nombre
    Integer cmCuadrados
    Integer hogarId
}
