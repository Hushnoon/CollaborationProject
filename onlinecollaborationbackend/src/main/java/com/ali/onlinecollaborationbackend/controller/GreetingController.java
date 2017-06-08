package com.ali.onlinecollaborationbackend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ali.onlinecollaborationbackend.model.DomainResponse;

@RestController
public class GreetingController {
	
	@GetMapping("/greeting")
	public ResponseEntity<DomainResponse> greeting()
	{
		return new ResponseEntity<DomainResponse>(new DomainResponse("Welcome to first spring rest controller",200),HttpStatus.OK);
	}
}
