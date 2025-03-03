package com.olivercdev.pruebaTecnica.repository.ConsultaUsuarios;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.olivercdev.pruebaTecnica.models.repository.Users.ConsultaUsuariosDto;
import com.olivercdev.pruebaTecnica.models.repository.Users.ConsultaUsuariosDtoResponse.User;
import com.olivercdev.pruebaTecnica.rest.client.ClRestApi;
import com.olivercdev.pruebaTecnica.rest.client.RestApiException;

@Repository
public class ConsultaUsuariosRepositoryImp implements ConsultaUsuariosRepository{

    @Autowired
    private ClRestApi restApi;

    @Override
    public List<User> getConsultaUsuarios(ConsultaUsuariosDto dto) throws RestApiException {
        List<User> response = new ArrayList<>();
        Map<String, Object> params = createParams(dto);
        response = runApiRequest(params);
        return response;
    }

    @Override
    public Map<String, Object> createParams(ConsultaUsuariosDto dto) {
        Map<String, Object> params = new HashMap<>();     
        
        return params;
    }

    @Override
    public List<User> runApiRequest(Map<String, Object> params) throws RestApiException {
        return restApi.get("com.bridge.ws.urlFakeStoreUsers", params, List.class, restApi.JSON);
    }
}
