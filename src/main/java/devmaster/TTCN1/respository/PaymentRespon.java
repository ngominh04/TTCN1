package devmaster.TTCN1.respository;

import devmaster.TTCN1.domain.PaymentMethod;
import devmaster.TTCN1.sql.SQL;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRespon extends CrudRepository<PaymentMethod,Integer> {
    @Query(value = SQL.ALL_PAYMENT,nativeQuery = true)
    List<PaymentMethod> getAllById();
    PaymentMethod getById(Integer idPayment);
    @Query(value = SQL.PAYMENT_ALL,nativeQuery = true)
    List<PaymentMethod> getAll();

    @Query(value = SQL.PAYMENT_ID,nativeQuery = true)
    PaymentMethod getPaymentById(Integer idPayment);
}
