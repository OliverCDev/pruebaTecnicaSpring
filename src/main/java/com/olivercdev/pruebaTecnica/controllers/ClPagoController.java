package com.olivercdev.pruebaTecnica.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.olivercdev.pruebaTecnica.models.request.ClConsultaOrdenesClienteRequest;
import com.olivercdev.pruebaTecnica.models.request.ClProductosRequest;
import com.olivercdev.pruebaTecnica.models.response.ClConsultaPagoOrdenesClienteResponse;
import com.olivercdev.pruebaTecnica.models.response.ClProductosResponse;
import com.olivercdev.pruebaTecnica.services.PagoOrdenes.ClPagoOrdenesCliente;

@RestController
public class ClPagoController {

    @Autowired
    public ClPagoOrdenesCliente servicePagoOrdenCliente;

    @PostMapping(value="/pago")

	public ResponseEntity<ClConsultaPagoOrdenesClienteResponse> autenticacion(
			@RequestBody ClConsultaOrdenesClienteRequest request) {
		return ResponseEntity.ok(this.servicePagoOrdenCliente.getPagoOrdenesCliente(request));
	}
}
