package devmaster.TTCN1.respository;

import devmaster.TTCN1.domain.Cart;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRespon extends CrudRepository<Cart,Integer> {
}
