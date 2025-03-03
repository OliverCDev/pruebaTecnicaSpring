package com.olivercdev.pruebaTecnica.models.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClConsultaPagoOrdenesClienteResponse extends ResponseModel{
    @JsonProperty("DetallePago")
    public DetallePago detallePago;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DetallePago{
        @JsonProperty("pIdOrden")
        public String pIdOrden;
        @JsonProperty("pFechaPago")
        public String pFechaPago;
        @JsonProperty("pValorPago")
        public String pValorPago;
        @JsonProperty("pIdCliente")
        public String pIdCliente;
        @JsonProperty("eProductos")
        public List<DetalleProducto> eProductos;  
    }
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DetalleProducto{
        @JsonProperty("pIdProducto")
        public String pIdProducto;
        @JsonProperty("pNombreProducto")        
        public String pCantidadProducto;
        @JsonProperty("pValorProducto")
        public String pValorProducto;
        @JsonProperty("pSubTotal")
        public String pSubTotal;
        
    }
}
