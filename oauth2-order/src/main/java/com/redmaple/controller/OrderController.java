package com.redmaple.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order")
public class OrderController {

	@GetMapping("/addOrder")
	public String addOrder() {
		System.out.println(">>>>> addOrder <<<<<");
		return "addOrder";
	}

}
