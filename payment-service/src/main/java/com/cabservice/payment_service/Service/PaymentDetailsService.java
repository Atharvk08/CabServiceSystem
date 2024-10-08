package com.cabservice.payment_service.Service;

import java.io.IOException;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cabservice.payment_service.Controller.PaymentController;
import com.cabservice.payment_service.Model.PaymentDetails;
import com.cabservice.payment_service.Repository.PaymentDetailsRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import ch.qos.logback.classic.LoggerContext;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
		
//		@Id
//		@GeneratedValue(strategy = GenerationType.IDENTITY)
//		private Long id;
//		private Double amount;
//		private String status;
//		private Double commissionAmount;
		
		PaymentDetails payment = new PaymentDetails();
		
		payment.setAmount(amount);
		payment.setCommissionAmount(amount*0.2);
		payment.setStatus("COMPLETED");
		payment.setTripId(tripId);
		
		return true;
	}
	
}
