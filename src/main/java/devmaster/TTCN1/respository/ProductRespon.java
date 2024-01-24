package devmaster.TTCN1.respository;

import devmaster.TTCN1.domain.Product;
import devmaster.TTCN1.projection.AllProductImage;
import devmaster.TTCN1.projection.IProduct;
import devmaster.TTCN1.sql.SQL;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRespon extends CrudRepository<Product, Integer> {
    Product findAllById(Integer idPro);

    @Query(value = SQL.TRANGCHU,nativeQuery = true) // SHOW SẢN PHẨM TRANG CHỦ
    List<Product> getAllProduct();

    @Query(value = SQL.ALL_PRODUCT_IMAGE,nativeQuery = true) // LẤY ẢNH CỦA SẢN PHẨM THEO ID SẢN PHẨM
    List<AllProductImage> getAllImage(Integer idPro);

    @Query(value = SQL.TRANGCHUCHITIET,nativeQuery = true) // SHOW CHI TIẾT SẢN PHẨM
    IProduct getProductChiTiet(Integer idPro);

    @Query(value = SQL.PRICE_DOWN,nativeQuery = true) // lọc theo giá tiền giảm dần
    List<Product> getPriceDown();
    @Query(value = SQL.PRICE_UP,nativeQuery = true) // lọc theo giá tiền tăng dần
    List<Product> getPriceUp();
    @Query(value = SQL.DUOI15,nativeQuery = true) // lọc theo giá tiền DƯỚI 15TR
    List<Product> getPrice_15();
    @Query(value = SQL.PRICE_15_25,nativeQuery = true) // lọc theo giá tiền 15tr _ 25tr
    List<Product> getPrice_15_25();
    @Query(value = SQL.PRICE_25_35,nativeQuery = true) // lọc theo giá tiền 25tr _ 35tr
    List<Product> getPrice_25_35();
    @Query(value = SQL.PRICE_35,nativeQuery = true) // lọc theo giá tiền trên 35tr
    List<Product> getPrice_35();
    @Query(value = SQL.ACCER,nativeQuery = true)// lọc theo hãng accer
    List<Product> getAccer();
    @Query(value = SQL.ASUS,nativeQuery = true)// lọc theo hãng asus
    List<Product> getAsus();
    @Query(value = SQL.DELL,nativeQuery = true)// lọc theo hãng dell
    List<Product> getDell();
    @Query(value = SQL.HP,nativeQuery = true)// lọc theo hãng hp
    List<Product> getHp();
    @Query(value = SQL.LENOVO,nativeQuery = true)// lọc theo hãng lenovo
    List<Product> getLenovo();
    @Query(value = SQL.APPLE,nativeQuery = true)// lọc theo hãng apple
    List<Product> getApple();

    @Query(value = SQL.PHUKIEN,nativeQuery = true) // lọc theo phụ kiện
    List<Product> getPhuKien();
}
