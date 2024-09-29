package com.cabservice.location_service.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cabservice.location_service.Model.GeoCoordinate;
import com.cabservice.location_service.Service.LocationService;

@RestController
@RequestMapping("/api/v1/location")
public class LocationController {


    @Autowired
    private LocationService locationService;

    @PostMapping("/save")
    public ResponseEntity<Void> saveLocation(@RequestParam Long userId,
                                              @RequestParam double latitude,
                                              @RequestParam double longitude) {
    	
        locationService.saveLocation( userId,latitude, longitude);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<GeoCoordinate> getLocation(@PathVariable String userId) {
        GeoCoordinate location = locationService.getLocation(userId);
        return ResponseEntity.ok(location);
    }

    @GetMapping("/nearby")
    public String getNearbyLocations(@RequestParam double latitude,
                                                             @RequestParam double longitude,
                                                             @RequestParam double radius) {
        String nearbyLocations = locationService.getNearByLocations(latitude, longitude, radius);
        return nearbyLocations;
    }
}
