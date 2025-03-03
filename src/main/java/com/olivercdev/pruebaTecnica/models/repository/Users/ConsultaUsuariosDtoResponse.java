package com.olivercdev.pruebaTecnica.models.repository.Users;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConsultaUsuariosDtoResponse {

    private User user;
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class User{
        private String id;
        private Name name;
        private String email;
        private String phone;
        private String username;
        private String company;
        private DetalleAddress address;
        private String password;

    }
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DetalleAddress{
        private String city;
        private String street;
        private String number;
        private String zipcode;
        private Geolocalization geolocation;
    }
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Geolocalization{
        private String lat;
        @JsonProperty("long")
        private String lng; 
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Name{
        private String firstname;
        private String lastname;

    }
}
