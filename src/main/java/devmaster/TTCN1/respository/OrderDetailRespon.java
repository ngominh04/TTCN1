package devmaster.TTCN1.respository;

import devmaster.TTCN1.domain.OrdersDetail;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailRespon extends CrudRepository<OrdersDetail,Integer> {
}
