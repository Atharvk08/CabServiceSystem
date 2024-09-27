package com.cabservice.map_service;

import java.io.IOException;

import org.springframework.stereotype.Service;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@Service
public class MapService {

	private static final String GRASSHOPER_API_KEY = "cceb673a-91d5-4e84-a9b2-adff379ce38d";
	private final OkHttpClient client = new OkHttpClient();
	

// Lat lon  -> the required address

	public String getChunkId(double lat, double lon) throws IOException {
		String url ="https://nominatim.openstreetmap.org/reverse?format=json&lat="+lat+"&lon="+lon;
		
		Request request= new Request.Builder()
				.url(url)
				.header("User-Agent", "Mozilla/5.0 (compatible; AcmeInc/1.0)")
				.build();
		System.out.println("Request" + request);
		try(Response response = client.newCall(request).execute()){
			System.out.println(response);
			if(response.isSuccessful()) {
				return response.body().string();
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "Not worked";

	}
	
//	Navigate
	
	public String getRoute(Double sourceLat, Double sourceLon,Double destLat, Double destLon ) throws IOException
	{
		// OSRM API URL for routing
        String url = String.format(
                "https://graphhopper.com/api/1/route?point=%f,%f&point=%f,%f&vehicle=car&key=%s",
                 sourceLat,sourceLon,  destLat,destLon,GRASSHOPER_API_KEY);
        
        Request request= new Request.Builder()
        		.url(url)
        		.header("User-Agent", "Mozilla/5.0 (compatible; AcmeInc/1.0)")
        		.build();
        
        try(Response response = client.newCall(request).execute()){
        	if(response.isSuccessful()) {
        		return response.body().string();
        	}else {
        		return "Failed to fetch route: "+response.code();
        	}
        }
	}
	
	
	
	
}
