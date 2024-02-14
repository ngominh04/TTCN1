package devmaster.TTCN1.respository;

import devmaster.TTCN1.domain.Order;
import devmaster.TTCN1.sql.SQL;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRespon extends CrudRepository<Order,Integer> {
    @Query(value = SQL.ORDER,nativeQuery = true)
    List<Order> getOrderByStatus (Integer status);
}
