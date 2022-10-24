package com.fiuba.organizarteapp.dominio.excepciones

class OperacionNoPermitidaException extends Exception {

    OperacionNoPermitidaException(String errorMessage) {
        super(errorMessage)
    }

}
