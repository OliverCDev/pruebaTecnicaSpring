package com.olivercdev.pruebaTecnica.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClMensaje {

    @JsonProperty("pCodigo")
	private int codigo;
	@JsonProperty("pMensaje")
	private String mensaje;
}
