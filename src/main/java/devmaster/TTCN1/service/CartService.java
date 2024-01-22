package devmaster.TTCN1.service;

import devmaster.TTCN1.domain.Cart;
import devmaster.TTCN1.respository.CartRespon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CartService {
    @Autowired
    CartRespon cartRespon;

    public Cart save(Cart entity) {
        return cartRespon.save(entity);
    }

    public List<Cart> saveAll(List<Cart> entities) {
        return (List<Cart>) cartRespon.saveAll(entities);
    }

    public Optional<Cart> findById(Integer integer) {
        return cartRespon.findById(integer);
    }

    public boolean existsById(Integer integer) {
        return cartRespon.existsById(integer);
    }

    public List<Cart> findAll() {
        return (List<Cart>) cartRespon.findAll();
    }

    public List<Cart> findAllById(List<Integer> integers) {
        return (List<Cart>) cartRespon.findAllById(integers);
    }

    public long count() {
        return cartRespon.count();
    }

    public void deleteById(Integer integer) {
        cartRespon.deleteById(integer);
    }

    public void delete(Cart entity) {
        cartRespon.delete(entity);
    }

    public void deleteAllById(List<Integer> integers) {
        cartRespon.deleteAllById(integers);
    }

    public void deleteAll(List<Cart> entities) {
        cartRespon.deleteAll(entities);
    }

    public void deleteAll() {
        cartRespon.deleteAll();
    }
}
