package com.olivercdev.pruebaTecnica.models.repository.Ordenes;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConsultaOrdenesDtoResponse {

    public Ordenes ordenes;
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Ordenes{
        
        public String id;
        public String userId;
        public String date;
        public List<OrdenProductoDetalle> products;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class OrdenProductoDetalle{
        
        public String productId;
        public String quantity;
    }
}
