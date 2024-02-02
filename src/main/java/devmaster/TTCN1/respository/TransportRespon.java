package devmaster.TTCN1.respository;

import devmaster.TTCN1.domain.TransportMethod;
import devmaster.TTCN1.sql.SQL;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransportRespon extends CrudRepository<TransportMethod,Integer> {
    @Query(value = SQL.ALL_TRANSPORT,nativeQuery = true)
    List<TransportMethod> getAllById();
}
