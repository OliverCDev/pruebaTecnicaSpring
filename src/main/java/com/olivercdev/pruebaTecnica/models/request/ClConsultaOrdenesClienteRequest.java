package com.olivercdev.pruebaTecnica.models.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClConsultaOrdenesClienteRequest {

    @JsonProperty("pCodigo")
    private String pCodigo;
    @JsonProperty("pIdCliente")
    private String pIdCliente;

}
