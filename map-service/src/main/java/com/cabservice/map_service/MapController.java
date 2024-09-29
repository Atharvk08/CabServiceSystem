package com.cabservice.map_service;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;




@RestController
@RequestMapping("/api/v1/map")
public class MapController {
	
	@Autowired 
	MapService mapService;
	
	@GetMapping("/location")
	public String getLatLon(@RequestParam double lat, @RequestParam double lng) throws IOException {
		
		return mapService.getChunkId(lat, lng);
	}
	@GetMapping("/navigate")
	public String getRoute(@RequestParam double sourceLat,@RequestParam double sourceLon,
            @RequestParam double destLat,
            @RequestParam double destLon ) throws IOException{
     
		return mapService.getRoute(sourceLat, sourceLon, destLat, destLon);
		
     }
	
	@GetMapping("get-coordinates")
	public String getCoordinates(@RequestParam String q) {
		return mapService.getLatLong(q);
	}
	
	@GetMapping("/estimated-time-distance")
	public String getEstimatedTimeAndDistance(@RequestParam double sourceLat,@RequestParam double sourceLon,
            @RequestParam double destLat,
            @RequestParam double destLon ) throws IOException {
		return mapService.getEstimatedTimeAndDistance(sourceLat, sourceLon, destLat, destLon);
	}
	

}
