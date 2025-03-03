package com.olivercdev.pruebaTecnica.rest.client;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.olivercdev.pruebaTecnica.models.response.ClProductosResponse.Producto;

@Component
public class ClRestApi {

    private RestTemplate restTemplate;

    private enum METODO {
        GET, POST
    }

    @Autowired
    private Environment env;
    private ObjectMapper mapper;
    public final String JSON = "json";

    public ClRestApi() {
        this.restTemplate = new RestTemplate();
    }

    private String convertirParamsToJson(Map<String, Object> params) {

        mapper = new ObjectMapper();

        try {
            return mapper.writeValueAsString(params);
        } catch (JsonProcessingException e) {
            return "{}";
        }

    }

    private String generarUrl(String nombreServicio, Map<String, Object> params, boolean isEncoded)
            throws RestApiException {
        String url = env.getProperty(nombreServicio);
        if (url == null || "".equals(url.trim())) {
            throw new RestApiException("Nombre del api no se encuentra configurado.");
        }
        if (params == null) {

            return url;
        }
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            String llave = entry.getKey();
            if (!entry.getValue().toString().contains("@")) {
                String valor;
                if (isEncoded) {
                    valor = URLEncoder.encode(entry.getValue().toString(), StandardCharsets.UTF_8);
                } else {
                    valor = entry.getValue().toString();
                }
                url = url.replace(llave, valor);
            } else {
                url = url.replace(llave, entry.getValue().toString());
            }
        }

        return url;
    }

    private <T> T ejecutarServicio(String nombreServicio, METODO metodo, Map<String, Object> params, Class<T> clase,
            String acceptType)
            throws RestApiException {
        T response = null;
        try {
            String url = this.generarUrl(nombreServicio,null);

            if (metodo == METODO.GET) {

                HttpHeaders headers = new HttpHeaders();

                MediaType mediaType = new MediaType("application", acceptType);
                List<MediaType> acceptableMediaTypes = new ArrayList<>();
                acceptableMediaTypes.add(mediaType);

                headers.setAccept(acceptableMediaTypes);
                HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

                ResponseEntity<List<T>> responseEntity = restTemplate.exchange(
                    url, HttpMethod.GET, requestEntity, 
                    new ParameterizedTypeReference<List<T>>() {}, // <-- Aquí
                    params
              );

                response = (T) responseEntity.getBody();

                var templateHandler = restTemplate.getUriTemplateHandler();
                var uriExpanded = templateHandler.expand(url, params);

            } else if (metodo == METODO.POST) {

                String data = params == null ? null : this.convertirParamsToJson(params);

                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                HttpEntity<String> entity = new HttpEntity<String>(data, headers);

                MediaType mediaType = new MediaType("application", acceptType);
                List<MediaType> acceptableMediaTypes = new ArrayList<>();
                acceptableMediaTypes.add(mediaType);

                headers.setAccept(acceptableMediaTypes);

                response = this.restTemplate.postForObject(url, entity, clase);
            }

        } catch (RestClientException e) {
            e.printStackTrace();

            throw e;
        } catch (RestApiException e) {
            e.printStackTrace();

            throw e;
        }
        return response;
    }

    /**
     * Ejecuta una llamada un servicio rest mediante el método POST y devuelve una
     * representación de la respuesta convertida en un objeto
     *
     * @param <T>
     * @param nombreServicio clave para buscar url en application.properties
     * @param body           objeto que contiene datos para enviar al servicio,
     *                       puede ser null
     * @param clase          clase que representa la respuesta del servicio
     * @param acceptType     String para indicar el tipo de respuesta que se espera
     *                       con el método post
     * @return objeto que representa la respuesta del servicio
     * @throws RestApiException
     */
    public <T> T post(String nombreServicio, Map<String, Object> body, Class<T> clase, String acceptType)
            throws RestApiException {
        return this.ejecutarServicio(nombreServicio, METODO.POST, body, clase, acceptType);
    }

    private String generarUrl(String nombreServicio, Map<String, Object> params) throws RestApiException {
		String url = env.getProperty(nombreServicio);
		if (url == null || "".equals(url.trim())) {
			throw new RestApiException("Nombre del api no se encuentra configurado.");
		}
		if (params == null) {

			return url;
		}
		for (Map.Entry<String, Object> entry : params.entrySet()) {
			String llave = entry.getKey();
			if (!entry.getValue().toString().contains("@")) {
				String valor = URLEncoder.encode(entry.getValue().toString(), StandardCharsets.UTF_8);
				url = url.replace(llave, valor);
			} else {
				url = url.replace(llave, entry.getValue().toString());
			}
		}


		return url;
	}

    public <T> T get(String nombreServicio, Map<String, Object> body, Class<T> clase, String acceptType)
			throws RestApiException {
		return this.ejecutarServicio(nombreServicio, METODO.GET, body, clase, acceptType);
	}

}
