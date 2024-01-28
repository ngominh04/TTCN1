package devmaster.TTCN1.respository;

import devmaster.TTCN1.domain.Cart;
import devmaster.TTCN1.domain.Product;
import devmaster.TTCN1.projection.ICountCart;
import devmaster.TTCN1.sql.SQL;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRespon extends CrudRepository<Cart,Integer> {
    Cart findAllById(Integer idCart);
    @Query(value = SQL.CART,nativeQuery = true)
    List<Cart> getCart(Integer idCus);

    @Query(value = SQL.COUNTCART,nativeQuery = true)
    ICountCart getCount(Integer idCus);
}
