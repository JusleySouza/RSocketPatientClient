package com.ju.springboot.rsocket.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ju.springboot.rsocket.model.ClinicalData;
import com.ju.springboot.rsocket.model.Patient;

import reactor.core.publisher.Mono;

@RestController
public class RSocketPatientClientController {
	
	private final RSocketRequester rSocketRequester;
	
	Logger logger = LoggerFactory.getLogger(RSocketPatientClientController.class);
	
	public RSocketPatientClientController(@Autowired RSocketRequester.Builder builder) {
		this.rSocketRequester = builder.tcp("localhost", 7000);
	}
	
	@GetMapping("/request-response")
	public Mono<ClinicalData> requestResponse(Patient patient){
		logger.info("Sending the rsocket request for patient: "+ patient);
		return rSocketRequester.route("get-patient-data").data(patient).retrieveMono(ClinicalData.class);
	}
	
	@PostMapping("/fire-and-forget")
	public Mono<Void> fireAndForget( @RequestBody Patient patient){
		logger.info("Patient Being Checked Out: "+ patient);
		return rSocketRequester.route("patient-checkout").data(patient).retrieveMono(Void.class);
	}

}
