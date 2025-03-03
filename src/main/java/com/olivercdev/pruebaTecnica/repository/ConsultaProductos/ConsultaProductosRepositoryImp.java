package com.olivercdev.pruebaTecnica.repository.ConsultaProductos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.olivercdev.pruebaTecnica.models.repository.ConsultaProductosDto;
import com.olivercdev.pruebaTecnica.models.repository.ConsultaProductosDtoResponse;
import com.olivercdev.pruebaTecnica.rest.client.ClRestApi;
import com.olivercdev.pruebaTecnica.rest.client.RestApiException;

@Repository
public class ConsultaProductosRepositoryImp implements ConsultaProductosRepository {

    @Autowired
    private ClRestApi restApi;
    @Override
    public List<ConsultaProductosDtoResponse.Producto> getConsultaProductos(ConsultaProductosDto dto) throws RestApiException {
        List<ConsultaProductosDtoResponse.Producto> response = new ArrayList<>();
        Map<String, Object> params = createParams(dto);
        response = runApiRequest(params);
        return response;
    }

    @Override
    public Map<String, Object> createParams(ConsultaProductosDto dto) {
        Map<String, Object> params = new HashMap<>();     
        
        return params;
    }

    @Override
    public List<ConsultaProductosDtoResponse.Producto> runApiRequest(Map<String, Object> params) throws RestApiException {
        return restApi.get("com.bridge.ws.urlFakeStoreProductos", params, List.class, restApi.JSON);
    }


}
