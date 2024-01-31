package devmaster.TTCN1.respository;

import devmaster.TTCN1.domain.Receiver;
import devmaster.TTCN1.sql.SQL;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReceiverRespon extends CrudRepository<Receiver,Integer> {
    // show toàn bộ receiver
    @Query(value = SQL.RECEIVER,nativeQuery = true)
    List<Receiver> getAllReceiver(Integer idCus);
}
