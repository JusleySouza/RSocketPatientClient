package com.ju.springboot.rsocket.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RSocketPatientClientController {
	
	private final RSocketRequester rSocketRequester;
	
	public RSocketPatientClientController(@Autowired RSocketRequester.Builder builder) {
		this.rSocketRequester = builder.tcp("localhost", 7000);
	}
	
	

}
