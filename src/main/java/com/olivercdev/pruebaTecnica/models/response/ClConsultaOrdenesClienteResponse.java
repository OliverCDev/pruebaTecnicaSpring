package com.olivercdev.pruebaTecnica.models.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClConsultaOrdenesClienteResponse extends ResponseModel {

    @JsonProperty("pIdCliente")
    private String idCliente;
 
    @JsonProperty("eOrdenes")
    public List<Ordenes> ordenes;
    
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Ordenes{
        @JsonProperty("pId")
        public String id;
        @JsonProperty("pUserId")
        public String userId;
        @JsonProperty("pDate")
        public String date;
        @JsonProperty("eDetalleOrdenes")
        public List<OrdenProductoDetalle> products;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class OrdenProductoDetalle{
        @JsonProperty("pProductoId")
        public String productId;
        @JsonProperty("pCantidadProducto")
        public String quantity;
    }
}
