package com.olivercdev.pruebaTecnica.models.repository;

import org.apache.logging.log4j.util.ProcessIdUtil;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConsultaProductosDtoResponse {
    
    Producto producto;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Producto {
        private String id;
        private String title;
        private float price;
        private String category;
        private String image;
        private ClasificacionDetalle rating;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ClasificacionDetalle {
        private float rate;
        private int count;
    }
}
