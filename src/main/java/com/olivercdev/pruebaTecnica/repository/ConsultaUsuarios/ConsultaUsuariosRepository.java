package com.olivercdev.pruebaTecnica.repository.ConsultaUsuarios;

import java.util.List;
import java.util.Map;

import com.olivercdev.pruebaTecnica.models.repository.Users.ConsultaUsuariosDto;
import com.olivercdev.pruebaTecnica.models.repository.Users.ConsultaUsuariosDtoResponse;
import com.olivercdev.pruebaTecnica.rest.client.RestApiException;

public interface ConsultaUsuariosRepository {
    
    public List<ConsultaUsuariosDtoResponse.User> getConsultaUsuarios(ConsultaUsuariosDto dto)throws RestApiException;

    public Map<String,Object> createParams(ConsultaUsuariosDto dto);

    public List<ConsultaUsuariosDtoResponse.User> runApiRequest(Map<String,Object> params)throws RestApiException;
}
