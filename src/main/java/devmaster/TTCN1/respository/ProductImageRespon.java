package devmaster.TTCN1.respository;

import devmaster.TTCN1.domain.ProductImage;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductImageRespon extends CrudRepository<ProductImage,Integer > {
    @Query(value = "select product_images.* from product_images \n" +
            "    inner JOIN material.product p on product_images.ID_PRODUCT = p.ID\n" +
            "where p.ID =?",nativeQuery = true)
    List<ProductImage> getAllByIdPro(Integer idPro);

    @Query(value = "select product_images.* from product_images\n" +
            "            where product_images.ID_PRODUCT =?",nativeQuery = true)
    List<ProductImage> getByIdPro(Integer idPro);

    @Query(value = "select * from product_images where ID =?",nativeQuery = true)
    ProductImage getById(Integer idImg);

}
