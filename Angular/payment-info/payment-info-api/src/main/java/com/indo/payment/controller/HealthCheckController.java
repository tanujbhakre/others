package com.indo.payment.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/healthcheck")
public class HealthCheckController {

	@RequestMapping(method = RequestMethod.GET)
	public String healthCheck() {
		return "running";
	}

}
