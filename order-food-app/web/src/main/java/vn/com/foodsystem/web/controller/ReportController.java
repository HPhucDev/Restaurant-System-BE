package vn.com.foodsystem.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vn.com.foodsystem.business.service.OrderService;
import vn.com.foodsystem.dao.model.OrderModel;
import vn.com.foodsystem.web.converter.OrderConverterService;
import vn.com.foodsystem.web.dto.OrderDTO;
import vn.com.foodsystem.web.requesthandling.ReportByDayRequest;

@RestController
@RequestMapping("/api/reports")
public class ReportController {
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private OrderConverterService orderConverterService;
	
	@PostMapping("/day")
	public List<OrderDTO> reportByDay(@RequestBody ReportByDayRequest date){
		List<OrderModel> orderModels=orderService.reportByDay(date.getDate());
		return orderConverterService.convertToDTO(orderModels);
	}
	@GetMapping("/year")
	public List<OrderDTO> reportByYear(@RequestParam int year){
		List<OrderModel> orderModels=orderService.reportByYear(year);
		return orderConverterService.convertToDTO(orderModels);
	}
	@GetMapping("/month")
	public List<OrderDTO> reportByMonth(@RequestParam("year") int year,@RequestParam("month") int month){
		List<OrderModel> orderModels=orderService.reportByMonth(year, month);
		return orderConverterService.convertToDTO(orderModels);
	}
	@GetMapping("/quarter")
	public List<OrderDTO> reportByQuarter(@RequestParam("year") int year,@RequestParam("quarter") int quarter){
		List<OrderModel> orderModels=orderService.reportByQuarter(year, quarter);
		return orderConverterService.convertToDTO(orderModels);
	}
	
	
	
}
