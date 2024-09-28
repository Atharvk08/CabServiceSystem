package com.cabservice.payment_service.Service;

import java.io.IOException;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cabservice.payment_service.Controller.PaymentController;
import com.cabservice.payment_service.Model.PaymentDetails;
import com.cabservice.payment_service.Repository.PaymentDetailsRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import ch.qos.logback.classic.LoggerContext;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@Service
public class PaymentDetailsService {
	
	private static final String GRASSHOPER_API_KEY = "cceb673a-91d5-4e84-a9b2-adff379ce38d";
	private final OkHttpClient client = new OkHttpClient();
	


	@Autowired
	private PaymentDetailsRepository paymentRepository;
	
	public PaymentDetails getPaymentById(Long id) {
		
		return paymentRepository.getReferenceById(id);
	}
	

	
	public Double getPrice(Long tripId) {
		double amount=0.0;
		
//		logic here
		
		return amount;
	}
	
	public Boolean makePayment(Long tripId, Double amount) {
		
//		logic here
		
		return true;
	}
	
}
