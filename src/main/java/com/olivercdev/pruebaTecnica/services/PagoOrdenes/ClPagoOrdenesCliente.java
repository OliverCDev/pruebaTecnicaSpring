package com.olivercdev.pruebaTecnica.services.PagoOrdenes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.olivercdev.pruebaTecnica.models.ClMensaje;
import com.olivercdev.pruebaTecnica.models.repository.ConsultaProductosDto;
import com.olivercdev.pruebaTecnica.models.repository.ConsultaProductosDtoResponse;
import com.olivercdev.pruebaTecnica.models.repository.Ordenes.ConsultaOrdenesDtoResponse;
import com.olivercdev.pruebaTecnica.models.repository.Users.ConsultaUsuariosDto;
import com.olivercdev.pruebaTecnica.models.repository.Users.ConsultaUsuariosDtoResponse;
import com.olivercdev.pruebaTecnica.models.request.ClConsultaOrdenesClienteRequest;
import com.olivercdev.pruebaTecnica.models.response.ClConsultaOrdenesClienteResponse;
import com.olivercdev.pruebaTecnica.models.response.ClConsultaPagoOrdenesClienteResponse;
import com.olivercdev.pruebaTecnica.repository.ConsultaProductos.ConsultaProductosRepository;
import com.olivercdev.pruebaTecnica.repository.ConsultaUsuarios.ConsultaUsuariosRepository;
import com.olivercdev.pruebaTecnica.rest.client.RestApiException;
import com.olivercdev.pruebaTecnica.services.Ordenes.ClConsultaOrdenesCliente;

@Repository
public class ClPagoOrdenesCliente {

    @Autowired
    private ClConsultaOrdenesCliente serviceOrdenesCliente;

    @Autowired
    private ConsultaUsuariosRepository repositoryUsuario;

    @Autowired
    private ConsultaProductosRepository repositoryProductos;

    @Autowired
    ObjectMapper objectMapper;

    public ClConsultaPagoOrdenesClienteResponse getPagoOrdenesCliente(ClConsultaOrdenesClienteRequest request){
        ClConsultaPagoOrdenesClienteResponse response = new ClConsultaPagoOrdenesClienteResponse();
        ClMensaje mensaje = new ClMensaje();
        try{
            ClConsultaOrdenesClienteResponse ordenesCliente = getOrdenesCliente(request);
            List<ConsultaUsuariosDtoResponse.User> usuarios = getUsuarios(request);
            List<ConsultaProductosDtoResponse.Producto> productos = getProductos(request);
            response = runBusinessLogic(ordenesCliente, usuarios, productos, response, request);
        }catch(Exception e){
            mensaje.setCodigo(4);
            mensaje.setMensaje("ERROR EN EL SERVICIO");
            e.printStackTrace();
            response.getEMensaje().add(mensaje);
        }
        return response;
    }

    public ClConsultaOrdenesClienteResponse getOrdenesCliente(ClConsultaOrdenesClienteRequest request){
        ClConsultaOrdenesClienteRequest requestOrdenes = new ClConsultaOrdenesClienteRequest();
        requestOrdenes.setPIdCliente(request.getPIdCliente());
        requestOrdenes.setPCodigo(request.getPCodigo());
        
        return serviceOrdenesCliente.getConsultaOrdenesCliente(requestOrdenes);
    }

    public List<ConsultaUsuariosDtoResponse.User> getUsuarios(ClConsultaOrdenesClienteRequest request) throws RestApiException{
        ConsultaUsuariosDto dto = new ConsultaUsuariosDto();
        dto.setPCodigo(request.getPCodigo());

        return repositoryUsuario.getConsultaUsuarios(dto);
    }

    public List<ConsultaProductosDtoResponse.Producto> getProductos(ClConsultaOrdenesClienteRequest request) throws RestApiException{
        ConsultaProductosDto dto = new ConsultaProductosDto();
        dto.setPCodigo(request.getPCodigo());

        return repositoryProductos.getConsultaProductos(dto);
    }

    public ClConsultaPagoOrdenesClienteResponse runBusinessLogic(ClConsultaOrdenesClienteResponse ordenesCliente , List<ConsultaUsuariosDtoResponse.User> usuarios, List<ConsultaProductosDtoResponse.Producto> productos , ClConsultaPagoOrdenesClienteResponse finalResponse, ClConsultaOrdenesClienteRequest request){
        finalResponse.setDetallePago(new ClConsultaPagoOrdenesClienteResponse.DetallePago());
        ClMensaje mensaje = new ClMensaje();
        finalResponse.getDetallePago().setEProductos(new ArrayList<>());
        Float totalPago = 0.0f;
        if(ordenesCliente.getOrdenes()!=null && usuarios!=null && productos!=null){
            for(ClConsultaOrdenesClienteResponse.Ordenes orden: ordenesCliente.getOrdenes()){
                if(orden.getId().equals(request.getPIdOrden())){
                    finalResponse.getDetallePago().setPIdOrden(orden.getId());
                    finalResponse.getDetallePago().setPFechaPago(getFechaHoraActual());
                    finalResponse.getDetallePago().setPIdCliente(request.getPIdCliente());
                    
                    for(ClConsultaOrdenesClienteResponse.OrdenProductoDetalle producto: orden.getProducts()){
                        for(Object obj: productos){
                            ConsultaProductosDtoResponse.Producto productoResponse = objectMapper.convertValue(obj, ConsultaProductosDtoResponse.Producto.class);
                            if(productoResponse.getId().equals(producto.getProductId())){
                                ClConsultaPagoOrdenesClienteResponse.DetalleProducto detalleProducto = new ClConsultaPagoOrdenesClienteResponse.DetalleProducto();
                                detalleProducto.setPIdProducto(productoResponse.getId());
                                detalleProducto.setPNombreProducto(productoResponse.getTitle());
                                detalleProducto.setPCantidadProducto(producto.getQuantity());
                                detalleProducto.setPValorProducto(Float.toString(productoResponse.getPrice()));
                                detalleProducto.setPSubTotal(Float.toString(Float.parseFloat(producto.getQuantity())*productoResponse.getPrice()));
                                totalPago += Float.parseFloat(producto.getQuantity())*productoResponse.getPrice();
                                finalResponse.getDetallePago().getEProductos().add(detalleProducto);
                            }
                        }
                    }

                }
            }
        }
        if(Float.toString(totalPago).equals(request.getPTotalPagar())){
            mensaje.setCodigo(0);
            mensaje.setMensaje("PAGO EXITOSO");
        }else{
            mensaje.setCodigo(1);
            mensaje.setMensaje("VALOR A PAGAR NO CORRESPONDE");
        }
        finalResponse.getDetallePago().setPValorPago(Float.toString(totalPago));
        finalResponse.getEMensaje().add(mensaje);
        return finalResponse;
    }

    public String getFechaHoraActual(){
        LocalDateTime fechaHoraActual = LocalDateTime.now();
        String fechaHoraStr = fechaHoraActual.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
        return fechaHoraStr;
    }

    

}
