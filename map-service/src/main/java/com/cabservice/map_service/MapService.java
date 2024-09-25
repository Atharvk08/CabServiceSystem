package com.cabservice.map_service;

import java.io.IOException;

import org.springframework.stereotype.Service;

import okhttp3.OkHttpClient;
import okhttp3.Request;

@Service
public class MapService {

	private final OkHttpClient client = new OkHttpClient();
	
	public String getChunkId(double lat, double lon) throws IOException {
		String url ="https://nominatim.openstreetmap.org/reverse?format=json&lat="+lat+"&lon="+lon;
		
		Request request= new Request.Builder().url(url).build();
		
		try(okhttp3.Response response = client.newCall(request).execute()){
			if(!response.isSuccessful()) {
				return response.body().toString();
			}
		}
		return "";
	}
	
	
	
}
