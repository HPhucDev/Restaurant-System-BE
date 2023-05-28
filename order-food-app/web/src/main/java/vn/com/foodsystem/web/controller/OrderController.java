package vn.com.foodsystem.web.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vn.com.foodsystem.business.service.OrderDetailService;
import vn.com.foodsystem.business.service.OrderPairingService;
import vn.com.foodsystem.business.service.OrderService;
import vn.com.foodsystem.business.service.OrderStatusService;
import vn.com.foodsystem.dao.enums.OrderDetailStatus;
import vn.com.foodsystem.dao.enums.OrderStatus;
import vn.com.foodsystem.dao.enums.TableName;
import vn.com.foodsystem.dao.model.OrderDetailModel;
import vn.com.foodsystem.dao.model.OrderModel;
import vn.com.foodsystem.dao.model.OrderPairingModel;
import vn.com.foodsystem.dao.model.OrderStatusModel;
import vn.com.foodsystem.web.converter.OrderConverterService;
import vn.com.foodsystem.web.converter.OrderDetailConverterService;
import vn.com.foodsystem.web.converter.UpdateInfoConverterService;
import vn.com.foodsystem.web.dto.OrderDTO;
import vn.com.foodsystem.web.dto.OrderDetailDTO;
import vn.com.foodsystem.web.error.exception.BadRequestException;
import vn.com.foodsystem.web.error.exception.ResourceNotFoundException;
import vn.com.foodsystem.web.requesthandling.MergeTableRequest;
import vn.com.foodsystem.web.requesthandling.OrderByCashierRequest;
import vn.com.foodsystem.web.requesthandling.OrderByUserRequest;

//@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@Autowired
	private OrderDetailService orderDetailService;

	@Autowired
	private OrderStatusService orderStatusService;

	@Autowired
	private OrderConverterService orderConverterService;

	@Autowired
	private UpdateInfoConverterService updateInfoConverterService;

	@Autowired
	private OrderDetailConverterService orderDetailConverterService;

	@Autowired
	private OrderPairingService orderPairingService; 
	
	@PostMapping
	public OrderDTO create(@RequestBody OrderDTO orderDTO) {
		OrderModel orderModel = orderConverterService.convertToEntity(orderDTO);
		orderModel.setId(null);
		orderModel.setDateIn(new Date());
		orderModel.setDateOut(null);
		orderModel.setTotal(0);
		orderService.save(orderModel);

		OrderStatusModel orderStatusModel = new OrderStatusModel();
		orderStatusModel.setDateTimeStatus(new Date());
		orderStatusModel.setName(OrderStatus.NEW);
		orderStatusModel.setOrder(orderModel);

		orderStatusService.save(orderStatusModel);

		return orderConverterService.convertToDTO(orderModel);
	}

	@GetMapping("/table")
	public List<OrderDTO> getByTable() {
		List<OrderModel> list = orderService.getOrderByTable();
		if (list == null) {
			throw new ResourceNotFoundException("Found not order");
		}

		return orderConverterService.convertToDTO(list);
	}

	@PatchMapping("/{id}")
	public OrderDTO updateStatus(@PathVariable("id") Long id, @RequestParam("status") OrderStatus orderStatus) {
		OrderModel orderModel = orderService.findOne(id);
		if (orderModel == null) {
			throw new ResourceNotFoundException("Found not order!");
		}
		OrderStatusModel orderStatusModel = orderStatusService.findByOrder(orderModel);
		if (orderStatusModel == null) {
			throw new ResourceNotFoundException("Found not order!");
		}
		orderStatusModel.setName(orderStatus);
		orderStatusService.save(orderStatusModel);

		return orderConverterService.convertToDTO(orderModel);
	}

	@PatchMapping
	public OrderDTO update(@RequestParam Long id, @RequestBody OrderDTO orderDTO) {
		OrderModel orderModel = orderService.findOne(id);
		if (orderModel == null) {
			throw new ResourceNotFoundException("Found not order!");
		}
		updateInfoConverterService.convertOrderInfo(orderModel, orderDTO);
		orderService.save(orderModel);

		return orderConverterService.convertToDTO(orderModel);
	}

	@GetMapping("/{id}")
	public OrderDTO getorder(@PathVariable("id") Long id) {
		OrderModel orderModel = orderService.findOne(id);
		if (orderModel == null) {
			throw new ResourceNotFoundException("Found not order!");
		}

		return orderConverterService.convertToDTO(orderModel);
	}
	@GetMapping("/findByTableName")
	public OrderDTO findByTableName(@RequestParam TableName tableName) {
		OrderModel orderModel = orderService.findOrderByTableMerge(tableName.toString());
		if (orderModel == null) {
			throw new ResourceNotFoundException("Found not order!");
		}

		return orderConverterService.convertToDTO(orderModel);
	}

	@GetMapping
	public OrderDTO findOrderByDetail(@RequestParam Long id) {
		OrderDetailModel orderDetailModel = orderDetailService.findOne(id);
		
		

		OrderModel orderModel = orderService.findByOrderDetailModels(orderDetailModel);

		return orderConverterService.convertToDTO(orderModel);
	}

	@PostMapping("/find-by-user")
	public List<OrderDTO> findOrderByUser(@Valid @RequestBody OrderByUserRequest orderByUserRequest) {
		List<OrderModel> orderModels = orderService.findByUser(orderByUserRequest.getIdUser(),
				orderByUserRequest.getDate());
		if (orderModels == null) {
			throw new ResourceNotFoundException("Found not order!");
		}

		return orderConverterService.convertToDTO(orderModels);
	}

	@PostMapping("/find-by-cashier")
	public List<OrderDTO> findOrderByCashier(@Valid @RequestBody OrderByCashierRequest orderByCashierRequest) {
		List<OrderModel> orderModels = orderService.findByCashier(orderByCashierRequest.getDate());
		if (orderModels == null) {
			throw new ResourceNotFoundException("Found not order!");
		}
		return orderConverterService.convertToDTO(orderModels);
	}

	@PatchMapping("/pay")
	public OrderDTO pay(@RequestParam("id") Long id) throws IOException {

		OrderModel orderModel = orderService.findOne(id);
		if (orderModel == null) {
			throw new ResourceNotFoundException("Found not order!");
		}
		
		OrderStatusModel orderStatusModel = orderStatusService.findByOrder(orderModel);
		if (orderStatusModel.getName().equals(OrderStatus.PAID)) {
			throw new BadRequestException("order paid!");
		}

		orderModel.setDateOut(new Date());
		List<OrderDetailModel> orderDetailModels = orderModel.getOrderDetailModels();
		double total = 0;
		for (OrderDetailModel o : orderDetailModels) {
			if (o.getStatus().equals(OrderDetailStatus.NEW)) {
				throw new BadRequestException("Order has not been filled in enough!");
			}
			if (o.getStatus().equals(OrderDetailStatus.ACCEPT) || o.getStatus().equals(OrderDetailStatus.FINISH)) {
				total += o.getQuantity() * o.getFoodItem().getPrice();
			}
		}
		orderModel.setTotal(total);
		orderStatusModel.setName(OrderStatus.PAID);
		orderStatusService.save(orderStatusModel);
		orderService.save(orderModel);
		List<OrderPairingModel> list =orderModel.getOrderPairingModels();
		if(list !=null) {
			for(OrderPairingModel o:list) {
				orderPairingService.deleteById(o.getId());
				OrderModel orderFind= orderService.findOrderByTableMerge(o.getTableNameJoin().toString());
				orderService.delete(orderFind);
			}
		}
		return orderConverterService.convertToDTO(orderModel);
	}



	
	@PostMapping("/merge/table-notnull")
	public OrderDTO mergeTable2(@RequestBody MergeTableRequest mergeTableRequest) throws IOException {
		OrderModel orderRoot = orderService.findOne(mergeTableRequest.getOrderRoot().getId());
		if (orderRoot == null) {
			throw new ResourceNotFoundException("Found not order!");
		}
		List<OrderDTO> orderDTOs = mergeTableRequest.getOrderList();
		for (OrderDTO orderDTO : orderDTOs) {

			OrderModel orderModel = orderService.findOne(orderDTO.getId());
			orderModel.getOrderStatus().get(0).setName(OrderStatus.MERGE);
			
			OrderPairingModel orderPairingModel= new OrderPairingModel();
			orderPairingModel.setOrder(orderRoot);
			orderPairingModel.setTableNameJoin(orderModel.getTableName());
			orderPairingService.save(orderPairingModel);
			
			orderPairingService.save(new OrderPairingModel(orderRoot.getTableName(), orderModel));
			
			orderService.save(orderModel);
			List<OrderDetailModel> orderDetailModels = orderModel.getOrderDetailModels();
			for (OrderDetailModel orderDetailModel : orderDetailModels) {
				orderDetailService.deleteById(orderDetailModel.getId());
				orderDetailModel.setId(null);
				orderDetailModel.setOrder(orderRoot);
				createOne(orderDetailConverterService.convertToDTO(orderDetailModel));
			}
		}
		return orderConverterService.convertToDTO(orderService.findOne(mergeTableRequest.getOrderRoot().getId()));
	}

	@PostMapping("/merge/table-null")
	public OrderDTO mergeTable(@RequestBody MergeTableRequest mergeTableRequest) throws IOException {
		OrderModel orderRoot = orderService.findOne(mergeTableRequest.getOrderRoot().getId());
		if (orderRoot == null) {
			throw new ResourceNotFoundException("Found not order!");
		}
		List<OrderDTO> orderDTOs = mergeTableRequest.getOrderList();
		for (OrderDTO orderDTO : orderDTOs) {

			OrderModel orderModel = orderService.findOne(orderDTO.getId());
			orderModel.getOrderStatus().get(0).setName(OrderStatus.CANCEL);
			orderService.save(orderModel);
			List<OrderDetailModel> orderDetailModels = orderModel.getOrderDetailModels();
			for (OrderDetailModel orderDetailModel : orderDetailModels) {
				orderDetailService.deleteById(orderDetailModel.getId());
				orderDetailModel.setId(null);
				orderDetailModel.setOrder(orderRoot);
				createOne(orderDetailConverterService.convertToDTO(orderDetailModel));
			}
		}
		return orderConverterService.convertToDTO(orderService.findOne(mergeTableRequest.getOrderRoot().getId()));
	}

	public void createOne(OrderDetailDTO orderDetailDTO) {
		List<OrderDetailModel> list = orderDetailService.findDetail(orderDetailDTO.getIdFoodItem(),
				orderDetailDTO.getIdOrder());
		if (list != null) {
			for (OrderDetailModel o : list) {
				if (o.getStatus().equals(orderDetailDTO.getStatus())) {
					o.setQuantity(o.getQuantity() + orderDetailDTO.getQuantity());
					orderDetailService.save(o);
					return;
				} else {
					OrderDetailModel orderDetailModelNew = orderDetailConverterService.convertToEntity(orderDetailDTO);

					orderDetailService.save(orderDetailModelNew);
					return;
				}
			}
		}
		OrderDetailModel orderDetailModelNew = orderDetailConverterService.convertToEntity(orderDetailDTO);

		orderDetailService.save(orderDetailModelNew);
	}
}

