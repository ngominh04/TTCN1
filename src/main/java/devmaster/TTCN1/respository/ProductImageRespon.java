package devmaster.TTCN1.respository;

import devmaster.TTCN1.domain.ProductImage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductImageRespon extends CrudRepository<ProductImage,Integer > {

}
