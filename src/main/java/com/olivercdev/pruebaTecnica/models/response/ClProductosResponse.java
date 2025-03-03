package com.olivercdev.pruebaTecnica.models.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClProductosResponse extends ResponseModel {
    
    @JsonProperty("eProductos")
    private List<Producto> productos;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Producto {
        @JsonProperty("pId")
        private String id;
        @JsonProperty("pTitle")
        private String title;
        @JsonProperty("pPrice")
        private float price;
        @JsonProperty("pCategory")
        private String category;
        @JsonProperty("pImage")
        private String image;
        @JsonProperty("Rating")
        private ClasificacionDetalle rating;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ClasificacionDetalle {
        @JsonProperty("pRate")
        private float rate;
        @JsonProperty("pCount")
        private int count;
    }
}
