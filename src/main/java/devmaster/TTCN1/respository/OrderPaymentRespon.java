package devmaster.TTCN1.respository;

import devmaster.TTCN1.domain.OrdersPayment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderPaymentRespon extends CrudRepository<OrdersPayment,Integer> {
}
