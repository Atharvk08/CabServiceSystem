package com.cabservice.payment_service.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Service
public class TripServiceClient {

	@Autowired 
	private RestTemplate restTemplate;
	
	public final static String TRIP_SERVICE_URL = "http://localhost:8081";
    private static final Logger logger = LoggerFactory.getLogger(MapServiceClient.class);
	
	public String getTripByTripId(Long tripId, Long userId)
	{
		final String PATH = String.format(
				"/api/v1/trip/get-trip?tripId=%f&userId=%f", tripId, userId);

		logger.info(TRIP_SERVICE_URL + PATH + ": ");
		
		var response = restTemplate.getForObject(TRIP_SERVICE_URL + PATH , String.class);
		ObjectMapper objectMapper = new ObjectMapper();
		
		try {
			JsonNode jsonNode= objectMapper.readTree(response);
			ObjectNode jsonResponse = objectMapper.createObjectNode();
			return objectMapper.writeValueAsString(jsonResponse);

		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "Error" ;
		
	}
}
