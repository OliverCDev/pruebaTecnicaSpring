package com.olivercdev.pruebaTecnica.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.olivercdev.pruebaTecnica.models.request.ClProductosRequest;
import com.olivercdev.pruebaTecnica.models.response.ClProductosResponse;
import com.olivercdev.pruebaTecnica.services.Productos.ClConsultaProductos;

@RestController
public class ClProductosController {

    @Autowired
    private ClConsultaProductos consultaProductosService;

    @PostMapping(value="/productos")
	public ResponseEntity<ClProductosResponse> autenticacion(
			@RequestBody ClProductosRequest request) {
		return ResponseEntity.ok(this.consultaProductosService.getProductosServices(request));
	}

}
