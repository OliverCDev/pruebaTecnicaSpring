package com.olivercdev.pruebaTecnica.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.olivercdev.pruebaTecnica.models.request.ClConsultaOrdenesClienteRequest;
import com.olivercdev.pruebaTecnica.models.request.ClConsultaOrdenesRequest;
import com.olivercdev.pruebaTecnica.models.request.ClProductosRequest;
import com.olivercdev.pruebaTecnica.models.response.ClConsultaOrdenesClienteResponse;
import com.olivercdev.pruebaTecnica.models.response.ClConsultaOrdenesResponse;
import com.olivercdev.pruebaTecnica.models.response.ClProductosResponse;
import com.olivercdev.pruebaTecnica.services.Ordenes.ClConsultaOrdenes;
import com.olivercdev.pruebaTecnica.services.Ordenes.ClConsultaOrdenesCliente;

@RestController
public class ClOrdenesController {

    @Autowired
    private ClConsultaOrdenes consultaOrdenesService;

    @Autowired
    private ClConsultaOrdenesCliente consultaOrdenesClienteService;

    @PostMapping(value="/ordenes")
	public ResponseEntity<ClConsultaOrdenesResponse> autenticacion(
			@RequestBody ClConsultaOrdenesRequest request) {
		return ResponseEntity.ok(this.consultaOrdenesService.getOrdenesServices(request));
	}

    @PostMapping(value="/ordenCliente")
	public ResponseEntity<ClConsultaOrdenesClienteResponse> autenticacion(
			@RequestBody ClConsultaOrdenesClienteRequest request) {
		return ResponseEntity.ok(this.consultaOrdenesClienteService.getConsultaOrdenesCliente(request));
	}

}
