package com.alejandro.tareadwesalejandro.dto;

import jakarta.validation.constraints.NotBlank;

public class RegistroEjemplarDTO {

    @NotBlank(message = "Debe seleccionar una planta.")
    private String codigoPlanta;

    @NotBlank(message = "El mensaje inicial no puede estar vacío.")
    private String mensajeInicial;

    public String getCodigoPlanta() {
        return codigoPlanta;
    }

    public void setCodigoPlanta(String codigoPlanta) {
        this.codigoPlanta = codigoPlanta;
    }

    public String getMensajeInicial() {
        return mensajeInicial;
    }

    public void setMensajeInicial(String mensajeInicial) {
        this.mensajeInicial = mensajeInicial;
    }
}

