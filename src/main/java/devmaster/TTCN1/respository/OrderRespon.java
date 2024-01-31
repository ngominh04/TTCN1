package devmaster.TTCN1.respository;

import devmaster.TTCN1.domain.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRespon extends CrudRepository<Order, Integer> {
}
