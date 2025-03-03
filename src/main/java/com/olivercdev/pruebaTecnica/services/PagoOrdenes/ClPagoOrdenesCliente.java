package com.olivercdev.pruebaTecnica.services.PagoOrdenes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.olivercdev.pruebaTecnica.models.request.ClConsultaOrdenesClienteRequest;
import com.olivercdev.pruebaTecnica.models.response.ClConsultaPagoOrdenesClienteResponse;
import com.olivercdev.pruebaTecnica.repository.ConsultaOrdenes.ConsultaOrdenesRepository;

@Repository
public class ClPagoOrdenesCliente {

    @Autowired
    private ConsultaOrdenesRepository repository;

    @Autowired
    ObjectMapper objectMapper;

    public ClConsultaPagoOrdenesClienteResponse getPagoOrdenesCliente(ClConsultaOrdenesClienteRequest request){
        ClConsultaPagoOrdenesClienteResponse response = new ClConsultaPagoOrdenesClienteResponse();

        return response;
    }
}
