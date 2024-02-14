package devmaster.TTCN1.service;

import devmaster.TTCN1.domain.Category;
import devmaster.TTCN1.respository.CategoryRespon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    CategoryRespon categoryRespon;

    public <S extends Category> S save(S entity) {
        return categoryRespon.save(entity);
    }

    public <S extends Category> Iterable<S> saveAll(Iterable<S> entities) {
        return categoryRespon.saveAll(entities);
    }

    public Optional<Category> findById(Integer integer) {
        return categoryRespon.findById(integer);
    }

    public boolean existsById(Integer integer) {
        return categoryRespon.existsById(integer);
    }

    public Iterable<Category> findAll() {
        return categoryRespon.findAll();
    }

    public Iterable<Category> findAllById(Iterable<Integer> integers) {
        return categoryRespon.findAllById(integers);
    }

    public long count() {
        return categoryRespon.count();
    }

    public void deleteById(Integer integer) {
        categoryRespon.deleteById(integer);
    }

    public void delete(Category entity) {
        categoryRespon.delete(entity);
    }

    public void deleteAllById(Iterable<? extends Integer> integers) {
        categoryRespon.deleteAllById(integers);
    }

    public void deleteAll(Iterable<? extends Category> entities) {
        categoryRespon.deleteAll(entities);
    }

    public void deleteAll() {
        categoryRespon.deleteAll();
    }
}
