package devmaster.TTCN1.respository;

import devmaster.TTCN1.domain.Evaluate;
import devmaster.TTCN1.projection.IEvaluate;
import devmaster.TTCN1.sql.SQL;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EvaluateRespon extends CrudRepository<Evaluate,Integer> {
    @Query(value = SQL.EVALUATE_IDORDER_IDCUS,nativeQuery = true)
    IEvaluate getEvaluate_IdOrder_IdCus(Integer idOrder, Integer idCus);

    @Query(value = SQL.EVALUATE_PRO,nativeQuery = true)
    List<IEvaluate> getAllEvaluateByPro(Integer idPro);
}
