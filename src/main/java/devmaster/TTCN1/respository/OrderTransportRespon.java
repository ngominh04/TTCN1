package devmaster.TTCN1.respository;

import devmaster.TTCN1.domain.OrdersTransport;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderTransportRespon extends CrudRepository<OrdersTransport,Integer> {
}
