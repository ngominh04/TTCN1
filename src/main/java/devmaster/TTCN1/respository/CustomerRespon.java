package devmaster.TTCN1.respository;

import devmaster.TTCN1.domain.Customer;
import devmaster.TTCN1.sql.SQL;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRespon extends CrudRepository<Customer, Integer> {
    @Query(value = SQL.CUSTOMER,nativeQuery = true)
    Customer getCustomer(String username);
    @Query(value = SQL.CUSTOMER_BY_ID,nativeQuery = true)
    Customer getCustomerById(Integer idCus);

    @Query(value = SQL.ALL_CUSTOMER,nativeQuery = true)
    List<Customer> getAllById();
}
