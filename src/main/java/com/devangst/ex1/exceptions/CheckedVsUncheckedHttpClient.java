package com.devangst.ex1.exceptions;

import com.devangst.ex1.google.GooglePlaceResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

public class CheckedVsUncheckedHttpClient {

    String key = "AIzaSyBDTguTjV7Y9RYR8O2S7icG15GHmjX15w4";

    String googlePlaceUrl = "https://maps.googleapis.com/maps/api/place/details/json?key=%s&placeid=%s";

    private RestTemplate restTemplate;

    private ObjectMapper mapper = new ObjectMapper();

    public CheckedVsUncheckedHttpClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public GooglePlaceResponse googlePlaceChecked(String placeId) throws ServiceUnavailableException, IOException {
        try {
            String json = restTemplate.getForObject(String.format(googlePlaceUrl, key, placeId), String.class);
            return mapper.readValue(json, GooglePlaceResponse.class);
        } catch (RestClientException ex) {
            throw new ServiceUnavailableException(ex);
        }
    }

    public GooglePlaceResponse googlePlaceUnchecked(String placeId) throws Exception {
        String json = restTemplate.getForObject(String.format(googlePlaceUrl, key, placeId), String.class);
        return mapper.readValue(json, GooglePlaceResponse.class);
    }
}
