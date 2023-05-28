package vn.com.foodsystem.web.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vn.com.foodsystem.business.service.OrderDetailService;
import vn.com.foodsystem.dao.enums.OrderDetailStatus;
import vn.com.foodsystem.dao.model.OrderDetailModel;
import vn.com.foodsystem.web.converter.OrderDetailConverterService;
import vn.com.foodsystem.web.converter.UpdateInfoConverterService;
import vn.com.foodsystem.web.dto.OrderDetailDTO;
import vn.com.foodsystem.web.error.exception.BadRequestException;
import vn.com.foodsystem.web.error.exception.ResourceNotFoundException;
import vn.com.foodsystem.web.requesthandling.OrderDetailIDRequest;

@RestController
@RequestMapping("/order-details")
public class OrderDetailController {

	@Autowired
	private OrderDetailService orderDetailService;
	
	@Autowired
	private UpdateInfoConverterService updateInfoConverterService;

	@Autowired
	private OrderDetailConverterService orderDetailConverterService;

	@PostMapping
	public OrderDetailDTO create(@RequestBody OrderDetailDTO orderDetailDTO) {

		return createOne(orderDetailDTO);

	}

	@PostMapping("/list")
	public List<OrderDetailDTO> createList(@RequestBody List<OrderDetailDTO> orderDetailDTOs) {
		List<OrderDetailDTO> list = new ArrayList<>();
		for (OrderDetailDTO orderDetailDTO : orderDetailDTOs) {
			list.add(createOne(orderDetailDTO));
		}

		return list;
	}

	public OrderDetailDTO createOne(OrderDetailDTO orderDetailDTO) {
		List<OrderDetailModel> list = orderDetailService.findDetail(orderDetailDTO.getIdFoodItem(),
				orderDetailDTO.getIdOrder());
		if (list != null) {
			for (OrderDetailModel o : list) {
				if (o.getStatus().equals(orderDetailDTO.getStatus())) {
					o.setQuantity(o.getQuantity() + orderDetailDTO.getQuantity());
					orderDetailService.save(o);
					return orderDetailConverterService.convertToDTO(o);
				} else {
					OrderDetailModel orderDetailModelNew = orderDetailConverterService.convertToEntity(orderDetailDTO);

					orderDetailService.save(orderDetailModelNew);
					return orderDetailConverterService.convertToDTO(orderDetailModelNew);
				}
			}
		}
		OrderDetailModel orderDetailModelNew = orderDetailConverterService.convertToEntity(orderDetailDTO);

		orderDetailService.save(orderDetailModelNew);
		return orderDetailConverterService.convertToDTO(orderDetailModelNew);
	}
	
	
	@DeleteMapping
	public String delete(@RequestParam Long id) throws IOException {
		OrderDetailModel orderDetailModel= orderDetailService.findOne(id);
		if(orderDetailModel == null) {
			throw new ResourceNotFoundException("Found not order detail!");
		}
		if(orderDetailModel.getStatus().equals(OrderDetailStatus.ACCEPT) || orderDetailModel.getStatus().equals(OrderDetailStatus.FINISH)) {
			throw new BadRequestException("Cannot delete order detail because status is ACCEPT or FINISH");
		}
		orderDetailService.deleteById(id);
		return "Delete success!";
	}
	
	@PostMapping("/find")
	public List<OrderDetailDTO> findOrderDetail(@RequestBody OrderDetailIDRequest orderDetailIDRequest) {
		List<OrderDetailModel> orderDetailModel = orderDetailService.findDetail(orderDetailIDRequest.getIdFoodItem(),
				orderDetailIDRequest.getIdOrder());

		if (orderDetailModel == null) {
			throw new ResourceNotFoundException("Found not order detail!");
		}
		return orderDetailConverterService.convertToDTO(orderDetailModel);
	}

	@GetMapping
	public OrderDetailDTO findById(@RequestParam Long id) {
		OrderDetailModel orderDetailModel = orderDetailService.findOne(id);
		if (orderDetailModel == null) {
			throw new ResourceNotFoundException("Found not order detail!");
		}
		return orderDetailConverterService.convertToDTO(orderDetailModel);
	}

	@PatchMapping
	public OrderDetailDTO update(@RequestParam Long id, @RequestBody OrderDetailDTO orderDetailDTO) {
		OrderDetailModel orderDetailModel = orderDetailService.findOne(id);
		if (orderDetailModel == null) {
			throw new ResourceNotFoundException("Found not order detail!");
		}
		
		updateInfoConverterService.convertOrderDetailInfo(orderDetailModel, orderDetailDTO);
		
		orderDetailService.save(orderDetailModel);
		return orderDetailConverterService.convertToDTO(orderDetailService.findOne(id));

	}

}
