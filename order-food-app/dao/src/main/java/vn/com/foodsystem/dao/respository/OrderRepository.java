package vn.com.foodsystem.dao.respository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import vn.com.foodsystem.dao.enums.TableName;
import vn.com.foodsystem.dao.model.OrderDetailModel;
import vn.com.foodsystem.dao.model.OrderModel;

public interface OrderRepository extends JpaRepository<OrderModel, Long>, JpaSpecificationExecutor<OrderModel> {
	@Query(value = "SELECT * FROM foodsystem.`order` o join foodsystem.order_status os on o.id = os.id_order where os.name not like 'Đã thanh toán' and os.name not like 'Hủy bỏ'", nativeQuery = true)
	public List<OrderModel> getOrderByTable();
	
	@Query(value = "SELECT * FROM foodsystem.order o where o.id_user = :id and o.date_in between :date and DATE_ADD( :date ,INTERVAL 1 DAY) ",nativeQuery = true)
	public List<OrderModel> findByUser(@Param("id") Long idUser,@Param("date") Date date);
	
	@Query(value = "SELECT * FROM foodsystem.order o where o.date_in between :date and DATE_ADD( :date ,INTERVAL 1 DAY) ",nativeQuery = true)
	public List<OrderModel> findByCashier(@Param("date") Date date);
	
	
	@Query(value = "SELECT * FROM foodsystem.order o JOIN foodsystem.order_detail od on o.id = od.id_order \r\n"
			+ "where o.id= ?1 and od.status not like 'Mới' and od.status not like 'Từ chối'",nativeQuery = true)
	public OrderModel getByid(Long id);
	
	public OrderModel findByOrderDetailModels(OrderDetailModel orderDetailModel);
	
	@Query(value = "SELECT * FROM foodsystem.order o join foodsystem.order_status os on o.id = os.id_order where os.name like 'Gộp bàn' and o.table_name like ?1",nativeQuery = true)
	public OrderModel findOrderByTableMerge(String tableName); 
	
	public List<OrderModel> findByTableName(TableName tableName);
	
	@Query(value = "SELECT * FROM foodsystem.order o join foodsystem.order_status os on o.id = os.id_order where os.name = 'Đã thanh toán' and o.date_in between ?1 and DATE_ADD( ?1 ,INTERVAL 1 DAY) ORDER BY o.id DESC",nativeQuery = true)
	public List<OrderModel> reportByDay(Date date);
	
	@Query(value = "SELECT * FROM foodsystem.order o join foodsystem.order_status os on o.id = os.id_order where os.name = 'Đã thanh toán' and YEAR(o.date_in) = ?1 ORDER BY o.id DESC",nativeQuery = true)
	public List<OrderModel> reportByYear(int year);
	
	@Query(value = "SELECT * FROM foodsystem.order o join foodsystem.order_status os on o.id = os.id_order where os.name = 'Đã thanh toán' and YEAR(o.date_in) = :year and MONTH(o.date_in)= :month ORDER BY o.id DESC",nativeQuery = true)
	public List<OrderModel> reportByMonth(@Param("year") int year ,@Param("month")int month);
	
	@Query(value = "SELECT * FROM foodsystem.order o join foodsystem.order_status os on o.id = os.id_order where os.name = 'Đã thanh toán' AND YEAR(o.date_in) = ?1 and Quarter(o.date_in) = ?2 ORDER BY o.id DESC",nativeQuery = true)
	public List<OrderModel> reportByQuarter(int year, int quarter);
	
}
