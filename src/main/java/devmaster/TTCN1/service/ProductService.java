package devmaster.TTCN1.service;

import devmaster.TTCN1.domain.Product;
import devmaster.TTCN1.respository.ProductRespon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRespon productRespon;

    // phân trang ở trang chủ
    // https://techmaster.vn/posts/37233/spring-with-thymeleaf-pagination-for-a-list-phan-trang-voi-spring-va-thymeleaf
    public Page<Product> findPaginated(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Product> list;

        if (productRespon.getAllProduct().size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, productRespon.getAllProduct().size());
            list = productRespon.getAllProduct().subList(startItem, toIndex);
        }

        Page<Product> coursePage
                = new PageImpl<Product>(list, PageRequest.of(currentPage, pageSize), productRespon.getAllProduct().size());
        return coursePage;
    }
    // phân trang khi search
    public Page<Product> findPaginatedSearch(Pageable pageable,String name) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Product> list;

        if (productRespon.getAllName(name).size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, productRespon.getAllName(name).size());
            list = productRespon.getAllName(name).subList(startItem, toIndex);
        }

        Page<Product> coursePage
                = new PageImpl<Product>(list, PageRequest.of(currentPage, pageSize), productRespon.getAllName(name).size());
        return coursePage;
    }


    public <S extends Product> S save(S entity) {
        return productRespon.save(entity);
    }

    public <S extends Product> Iterable<S> saveAll(Iterable<S> entities) {
        return productRespon.saveAll(entities);
    }

    public Optional<Product> findById(Integer integer) {
        return productRespon.findById(integer);
    }

    public boolean existsById(Integer integer) {
        return productRespon.existsById(integer);
    }

    public Iterable<Product> findAll(Pageable pageable) {
        return productRespon.findAll();
    }

    public Iterable<Product> findAllById(Iterable<Integer> integers) {
        return productRespon.findAllById(integers);
    }

    public long count() {
        return productRespon.count();
    }

    public void deleteById(Integer integer) {
        productRespon.deleteById(integer);
    }

    public void delete(Product entity) {
        productRespon.delete(entity);
    }

    public void deleteAllById(Iterable<? extends Integer> integers) {
        productRespon.deleteAllById(integers);
    }

    public void deleteAll(Iterable<? extends Product> entities) {
        productRespon.deleteAll(entities);
    }

    public void deleteAll() {
        productRespon.deleteAll();
    }
}
