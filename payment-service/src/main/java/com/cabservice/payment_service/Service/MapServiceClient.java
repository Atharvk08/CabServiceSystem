package com.cabservice.payment_service.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import ch.qos.logback.classic.LoggerContext;

@Component
public class MapServiceClient {

	public final static String MAP_SERVICE_URL = "http://localhost:8081";

	@Value("${price.perKM}")
	private String ratePerKM;
	
    private static final Logger logger = LoggerFactory.getLogger(MapServiceClient.class);

	@Autowired
	private RestTemplate restTemplate;

//	fetch the distance and time from the Map-service and calculate the est time and price.
	public String getEstimatedPrice(Double sourceLat, Double sourceLon, Double destLat, Double destLon) {
		final String PATH = String.format(
				"/api/v1/map/estimated-time-distance?sourceLat=%f&sourceLon=%f&destLat=%f&destLon=%f", sourceLat,
				sourceLon, destLat, destLon);

		logger.info(MAP_SERVICE_URL + PATH + ": ");
		System.out.println(MAP_SERVICE_URL);
		
		var response = restTemplate.getForObject(MAP_SERVICE_URL + PATH, String.class);
		
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			JsonNode rootNode= objectMapper.readTree(response);
			Double distance = rootNode.path("distance_in_meters").asDouble();
			
//			est price calculation
			Double estimatedPrice =distance * Double.parseDouble(ratePerKM)/1000.0;
			ObjectNode jsonResponse = objectMapper.createObjectNode();
			
//			add the key values in json response
			jsonResponse.put("estimated_price", estimatedPrice);
			jsonResponse.put("estimated_time", rootNode.path("time_in_minutes").asDouble());
			
			return objectMapper.writeValueAsString(jsonResponse);
			
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
        logger.info("Received response from Map Service: {}", response);

        return "Error : while fetching the est price" ;
  

	}
}
