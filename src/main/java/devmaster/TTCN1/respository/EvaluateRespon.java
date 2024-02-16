package devmaster.TTCN1.respository;

import devmaster.TTCN1.domain.Evaluate;
import devmaster.TTCN1.projection.IEvaluate;
import devmaster.TTCN1.sql.SQL;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EvaluateRespon extends CrudRepository<Evaluate,Integer> {
    @Query(value = SQL.EVALUATE_IDORDER_IDCUS,nativeQuery = true)
    IEvaluate getEvaluate_IdOrder_IdCus(Integer idOrder, Integer idCus);
}
