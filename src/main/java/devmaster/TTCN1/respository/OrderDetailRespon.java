package devmaster.TTCN1.respository;

import devmaster.TTCN1.domain.OrdersDetail;
import devmaster.TTCN1.projection.IOrderDetails;
import devmaster.TTCN1.sql.SQL;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailRespon extends CrudRepository<OrdersDetail,Integer> {
    @Query(value = SQL.ORDER_DETAIL_BY_IDORDER,nativeQuery = true)
    List<IOrderDetails> getOrdersDetail_IdOrder(Integer idOrder);
}
