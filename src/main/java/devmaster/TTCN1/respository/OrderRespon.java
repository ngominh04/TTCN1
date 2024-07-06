package devmaster.TTCN1.respository;

import devmaster.TTCN1.domain.Order;
import devmaster.TTCN1.projection.IOrder;
import devmaster.TTCN1.sql.SQL;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRespon extends CrudRepository<Order,Integer> {

    @Query(value = "select *from `order` o where o.ID = ?",nativeQuery = true)
    Order finById(Integer idOrder);
    @Query(value = SQL.ORDER,nativeQuery = true)
    List<Order> getOrderByStatus (Integer status);
    @Query(value = SQL.ORDER_STATUS_IDCUS,nativeQuery = true)
    List<IOrder> getOrder_IdCus_Status(Integer idCus, Integer status);

    @Query(value = SQL.ORDER_BY_ID,nativeQuery = true)
    IOrder getOrderById(Integer idOrder);

    @Query(value = SQL.COUNT_ORDER_BY_STATIC_3,nativeQuery = true)
    IOrder getCountOrder3(Integer idCus);
}
