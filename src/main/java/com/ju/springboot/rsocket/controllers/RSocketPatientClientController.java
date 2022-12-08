package com.ju.springboot.rsocket.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ju.springboot.rsocket.model.ClinicalData;
import com.ju.springboot.rsocket.model.Patient;

import reactor.core.publisher.Mono;

@RestController
public class RSocketPatientClientController {
	
	private final RSocketRequester rSocketRequester;
	
	public RSocketPatientClientController(@Autowired RSocketRequester.Builder builder) {
		this.rSocketRequester = builder.tcp("localhost", 7000);
	}
	
	@GetMapping("/request-response")
	public Mono<ClinicalData> requestResponse(Patient patient){
		return rSocketRequester.route("get-patient-data").data(patient).retrieveMono(ClinicalData.class);
	}

}
