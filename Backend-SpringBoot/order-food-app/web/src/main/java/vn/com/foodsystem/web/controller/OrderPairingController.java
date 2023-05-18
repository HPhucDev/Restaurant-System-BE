package vn.com.foodsystem.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vn.com.foodsystem.business.service.OrderPairingService;
import vn.com.foodsystem.dao.model.OrderPairingModel;
import vn.com.foodsystem.web.converter.OrderPairingConverterService;
import vn.com.foodsystem.web.dto.OrderPairingDTO;

@RestController
@RequestMapping("/api/order-pairing")
public class OrderPairingController {
	
	@Autowired
	private OrderPairingService orderPairingService;
	
	@Autowired
	private OrderPairingConverterService orderPairingConverterService;
	
	@GetMapping
	public OrderPairingDTO find(@RequestParam Long id) {
		return orderPairingConverterService.convertToDTO(orderPairingService.findOne(id));
	}
}
