package com.olivercdev.pruebaTecnica.services.Ordenes;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.olivercdev.pruebaTecnica.models.ClMensaje;
import com.olivercdev.pruebaTecnica.models.repository.ConsultaProductosDto;
import com.olivercdev.pruebaTecnica.models.repository.ConsultaProductosDtoResponse;
import com.olivercdev.pruebaTecnica.models.repository.Ordenes.ConsultaOrdenesDto;
import com.olivercdev.pruebaTecnica.models.repository.Ordenes.ConsultaOrdenesDtoResponse;
import com.olivercdev.pruebaTecnica.models.request.ClConsultaOrdenesRequest;
import com.olivercdev.pruebaTecnica.models.request.ClProductosRequest;
import com.olivercdev.pruebaTecnica.models.response.ClConsultaOrdenesResponse;
import com.olivercdev.pruebaTecnica.models.response.ClProductosResponse;
import com.olivercdev.pruebaTecnica.repository.ConsultaOrdenes.ConsultaOrdenesRepository;
import com.olivercdev.pruebaTecnica.rest.client.RestApiException;

@Repository
public class ClConsultaOrdenes {

    @Autowired
    private ConsultaOrdenesRepository repository;

    @Autowired
    ObjectMapper objectMapper;

    public ClConsultaOrdenesResponse getOrdenesServices(ClConsultaOrdenesRequest request){
        ClMensaje mensaje = new ClMensaje();
        ClConsultaOrdenesResponse response = new ClConsultaOrdenesResponse();
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

     public List<ConsultaOrdenesDtoResponse.Ordenes> createResponseApi(ClConsultaOrdenesRequest request) throws RestApiException{
        ConsultaOrdenesDto dto = new ConsultaOrdenesDto();
        dto.setPCodigo(request.getPCodigo());

        List<ConsultaOrdenesDtoResponse.Ordenes> response = repository.getConsultaOrdenes(dto);
        System.out.println("Tipo de dato devuelto por el repository: " + response.getClass());
   
        return response;
    }

    public ClConsultaOrdenesResponse runBusinessLogic(List<ConsultaOrdenesDtoResponse.Ordenes> dtoResponse, ClConsultaOrdenesResponse response ){
        ClMensaje mensaje = new ClMensaje();
        if(dtoResponse != null){
            mensaje.setCodigo(0);
            mensaje.setMensaje("CONSULTA EXITOSA");
            response.setOrdenes(new ArrayList<>());
            for(Object obj : dtoResponse){
                ConsultaOrdenesDtoResponse.Ordenes orden = objectMapper.convertValue(obj, ConsultaOrdenesDtoResponse.Ordenes.class);
                ClConsultaOrdenesResponse.Ordenes ordenResponse = new ClConsultaOrdenesResponse.Ordenes();
                ordenResponse.setId(orden.getId());
                ordenResponse.setUserId(orden.getUserId());
                ordenResponse.setDate(orden.getDate());
                ordenResponse.setProducts(new ArrayList<>());
                for(ConsultaOrdenesDtoResponse.OrdenProductoDetalle detalle: orden.getProducts()){
                    ClConsultaOrdenesResponse.OrdenProductoDetalle producto = new ClConsultaOrdenesResponse.OrdenProductoDetalle();
                    producto.setProductId(detalle.getProductId());
                    producto.setQuantity(detalle.getQuantity());
                    ordenResponse.getProducts().add(producto);
                }
                response.getOrdenes().add(ordenResponse);
            }
        } else{
            mensaje.setCodigo(1);
            mensaje.setMensaje("NO SE ENCONTRARON ORDENES");
        }  
        response.getEMensaje().add(mensaje);     
        return response;
    }


}
