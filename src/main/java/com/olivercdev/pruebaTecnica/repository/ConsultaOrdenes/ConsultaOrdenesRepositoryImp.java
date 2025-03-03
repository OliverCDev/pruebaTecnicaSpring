package com.olivercdev.pruebaTecnica.repository.ConsultaOrdenes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.olivercdev.pruebaTecnica.models.repository.Ordenes.ConsultaOrdenesDto;
import com.olivercdev.pruebaTecnica.models.repository.Ordenes.ConsultaOrdenesDtoResponse;
import com.olivercdev.pruebaTecnica.models.repository.Ordenes.ConsultaOrdenesDtoResponse.Ordenes;
import com.olivercdev.pruebaTecnica.rest.client.ClRestApi;
import com.olivercdev.pruebaTecnica.rest.client.RestApiException;

@Repository
public class ConsultaOrdenesRepositoryImp implements ConsultaOrdenesRepository{

    @Autowired
    private ClRestApi restApi;
    @Override
    public List<ConsultaOrdenesDtoResponse.Ordenes> getConsultaOrdenes(ConsultaOrdenesDto dto) throws RestApiException {
        List<ConsultaOrdenesDtoResponse.Ordenes> response = new ArrayList<>();
        Map<String, Object> params = createParams(dto);
        response = runApiRequest(params);
        return response;
    }

    @Override
    public Map<String, Object> createParams(ConsultaOrdenesDto dto) {
        Map<String, Object> params = new HashMap<>();     
        
        return params;
    }

    @Override
    public List<ConsultaOrdenesDtoResponse.Ordenes> runApiRequest(Map<String, Object> params) throws RestApiException {
        return restApi.get("com.bridge.ws.urlFakeStoreOrdenes", params, List.class, restApi.JSON);
    }

}
