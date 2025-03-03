package com.olivercdev.pruebaTecnica.services.Productos;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.olivercdev.pruebaTecnica.models.ClMensaje;
import com.olivercdev.pruebaTecnica.models.repository.ConsultaProductosDto;
import com.olivercdev.pruebaTecnica.models.repository.ConsultaProductosDtoResponse;
import com.olivercdev.pruebaTecnica.models.request.ClProductosRequest;
import com.olivercdev.pruebaTecnica.models.response.ClProductosResponse;
import com.olivercdev.pruebaTecnica.repository.ConsultaProductos.ConsultaProductosRepository;
import com.olivercdev.pruebaTecnica.rest.client.RestApiException;


@Repository
public class ClConsultaProductos {

    @Autowired
    private ConsultaProductosRepository repository;

    @Autowired
    ObjectMapper objectMapper;

    public ClProductosResponse getProductosServices(ClProductosRequest request){
        ClProductosResponse response = new ClProductosResponse();
        ClMensaje mensaje = new ClMensaje();
        try{
            response = runBusinessLogic(createResponseApi(request), response);
        }catch(Exception e){
            mensaje.setCodigo(4);
            mensaje.setMensaje("ERROR EN EL SERVICIO");
            e.printStackTrace();
            response.getEMensaje().add(mensaje);
        }
        return response;
    }

    public List<ConsultaProductosDtoResponse.Producto> createResponseApi(ClProductosRequest request) throws RestApiException{
        ConsultaProductosDto dto = new ConsultaProductosDto();
        dto.setPCodigo(request.getPCodigo());

        return repository.getConsultaProductos(dto);
    }

    public ClProductosResponse runBusinessLogic(List<ConsultaProductosDtoResponse.Producto> dtoResponse,ClProductosResponse response ){
        ClMensaje mensaje = new ClMensaje();
        if(dtoResponse != null){
            mensaje.setCodigo(0);
            mensaje.setMensaje("CONSULTA EXITOSA");
            response.setProductos(new ArrayList<>());
            for(Object obj : dtoResponse){
                ConsultaProductosDtoResponse.Producto producto = objectMapper.convertValue(obj,  ConsultaProductosDtoResponse.Producto.class);
                ClProductosResponse.Producto productoResponse = new ClProductosResponse.Producto();
                productoResponse.setId(producto.getId());
                productoResponse.setTitle(producto.getTitle());
                productoResponse.setPrice(producto.getPrice());
                productoResponse.setCategory(producto.getCategory());
                productoResponse.setImage(producto.getImage());
                productoResponse.setRating(new ClProductosResponse.ClasificacionDetalle());
                productoResponse.getRating().setRate(producto.getRating().getRate());
                productoResponse.getRating().setCount(producto.getRating().getCount());
                response.getProductos().add(productoResponse);
            }
        }else{
            mensaje.setCodigo(1);
            mensaje.setMensaje("NO SE ENCONTRARON PRODUCTOS");
        }  
        response.getEMensaje().add(mensaje);     
        return response;        
    }
   
}
