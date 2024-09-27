package com.cabservice.map_service;

import java.io.IOException;

import org.springframework.stereotype.Service;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@Service
public class MapService {

	private final OkHttpClient client = new OkHttpClient();
	

// Lat lon  -> the required address

	public String getChunkId(double lat, double lon) throws IOException {
		String url ="https://nominatim.openstreetmap.org/reverse?format=json&lat="+lat+"&lon="+lon;
		
		Request request= new Request.Builder()
				.url(url)
				.header("User-Agent", "Mozilla/5.0 (compatible; AcmeInc/1.0)")
				.build();
		System.out.println("Request" + request);
		String res="";
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
	
	public String NavigateRoute(Double sourceLat, Double sourceLon,Double destLat, Double destLon ) throws IOException
	{
		// OSRM API URL for routing
        String url = String.format(
                "http://router.project-osrm.org/route/v1/driving/%f,%f;%f,%f?overview=full&geometries=geojson",
                sourceLon, sourceLat, destLon, destLat);
        
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
