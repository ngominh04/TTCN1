package devmaster.TTCN1.service;

import devmaster.TTCN1.domain.ProductImage;
import devmaster.TTCN1.respository.ProductImageRespon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductImageService {
    @Autowired
    ProductImageRespon productImageRespon;

    public <S extends ProductImage> S save(S entity) {
        return productImageRespon.save(entity);
    }

    public <S extends ProductImage> Iterable<S> saveAll(Iterable<S> entities) {
        return productImageRespon.saveAll(entities);
    }

    public Optional<ProductImage> findById(Integer integer) {
        return productImageRespon.findById(integer);
    }

    public boolean existsById(Integer integer) {
        return productImageRespon.existsById(integer);
    }

    public Iterable<ProductImage> findAll() {
        return productImageRespon.findAll();
    }

    public Iterable<ProductImage> findAllById(Iterable<Integer> integers) {
        return productImageRespon.findAllById(integers);
    }

    public long count() {
        return productImageRespon.count();
    }

    public void deleteById(Integer integer) {
        productImageRespon.deleteById(integer);
    }

    public void delete(ProductImage entity) {
        productImageRespon.delete(entity);
    }

    public void deleteAllById(Iterable<? extends Integer> integers) {
        productImageRespon.deleteAllById(integers);
    }

    public void deleteAll(Iterable<? extends ProductImage> entities) {
        productImageRespon.deleteAll(entities);
    }

    public void deleteAll() {
        productImageRespon.deleteAll();
    }
}
