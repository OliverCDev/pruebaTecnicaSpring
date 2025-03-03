package com.olivercdev.pruebaTecnica.repository.ConsultaProductos;

import java.util.List;
import java.util.Map;

import com.olivercdev.pruebaTecnica.models.repository.ConsultaProductosDto;
import com.olivercdev.pruebaTecnica.models.repository.ConsultaProductosDtoResponse;
import com.olivercdev.pruebaTecnica.rest.client.RestApiException;

public interface ConsultaProductosRepository {

    public List<ConsultaProductosDtoResponse.Producto> getConsultaProductos(ConsultaProductosDto dto)throws RestApiException;

    Map<String, Object> createParams(ConsultaProductosDto dto);

    List<ConsultaProductosDtoResponse.Producto> runApiRequest(Map<String, Object> params)throws RestApiException;

}

