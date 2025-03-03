package com.olivercdev.pruebaTecnica.repository.ConsultaOrdenes;

import java.util.List;
import java.util.Map;

import com.olivercdev.pruebaTecnica.models.repository.Ordenes.ConsultaOrdenesDto;
import com.olivercdev.pruebaTecnica.models.repository.Ordenes.ConsultaOrdenesDtoResponse;
import com.olivercdev.pruebaTecnica.rest.client.RestApiException;

public interface ConsultaOrdenesRepository {

    public List<ConsultaOrdenesDtoResponse.Ordenes> getConsultaOrdenes(ConsultaOrdenesDto dto)throws RestApiException;

    public Map<String,Object> createParams(ConsultaOrdenesDto dto);

    public List<ConsultaOrdenesDtoResponse.Ordenes> runApiRequest(Map<String,Object> params)throws RestApiException;
}
