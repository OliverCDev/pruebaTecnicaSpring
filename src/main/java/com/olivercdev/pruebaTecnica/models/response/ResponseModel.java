package com.olivercdev.pruebaTecnica.models.response;

import java.util.Collection;
import java.util.HashSet;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.olivercdev.pruebaTecnica.models.ClMensaje;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonInclude(value=Include.NON_NULL)
public class ResponseModel {
    @JsonProperty("vCodigo")
	private int vCodigo;
	
	@JsonProperty("eMensaje")
    private Collection<ClMensaje> eMensaje;
	
	public ResponseModel() {
		this.eMensaje = new HashSet<>();
	}
}
