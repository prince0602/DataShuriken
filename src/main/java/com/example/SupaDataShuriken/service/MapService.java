package com.example.SupaDataShuriken.service;


import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MapService {
    private final GeoApiContext context;

    public MapService(@Value("${google.map.api.key}") String apiKey) {
        this.context = new GeoApiContext.Builder()
                .apiKey(apiKey)
                .build();
    }

    public GeocodingResult[] getCoordinates(String location) throws Exception {
        return GeocodingApi.geocode(context, location).await();
    }

}

