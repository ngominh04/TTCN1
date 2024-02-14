package devmaster.TTCN1.respository;

import devmaster.TTCN1.domain.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRespon extends CrudRepository<Category, Integer> {
    Category findAllById(Integer idCate);
    @Query(value = "select * from category",nativeQuery = true)
    List<Category> getAll();
}
