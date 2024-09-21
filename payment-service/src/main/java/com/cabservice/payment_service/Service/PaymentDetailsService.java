package com.cabservice.payment_service.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cabservice.payment_service.Model.PaymentDetails;
import com.cabservice.payment_service.Repository.PaymentDetailsRepository;

@Service
public class PaymentDetailsService {
	
	@Autowired
	private PaymentDetailsRepository paymentRepository;
	
	public PaymentDetails getPaymentById(Long id) {
		
		return paymentRepository.getReferenceById(id);
	}
	
	public Double getEstimatedPrice(String lat1, String lon1, String lat2, String lon2) {
		Double amount=0.0;
		
		// Logic here for estimated price based on the lat lon of the source location and destination location
		
		return amount;
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
