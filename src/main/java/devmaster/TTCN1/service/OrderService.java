package devmaster.TTCN1.service;

import devmaster.TTCN1.domain.Order;
import devmaster.TTCN1.respository.OrderRespon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    OrderRespon orderRespon;

    public <S extends Order> S save(S entity) {
        return orderRespon.save(entity);
    }

    public <S extends Order> Iterable<S> saveAll(Iterable<S> entities) {
        return orderRespon.saveAll(entities);
    }

    public Optional<Order> findById(Integer integer) {
        return orderRespon.findById(integer);
    }

    public boolean existsById(Integer integer) {
        return orderRespon.existsById(integer);
    }

    public Iterable<Order> findAll() {
        return orderRespon.findAll();
    }

    public Iterable<Order> findAllById(Iterable<Integer> integers) {
        return orderRespon.findAllById(integers);
    }

    public long count() {
        return orderRespon.count();
    }

    public void deleteById(Integer integer) {
        orderRespon.deleteById(integer);
    }

    public void delete(Order entity) {
        orderRespon.delete(entity);
    }

    public void deleteAllById(Iterable<? extends Integer> integers) {
        orderRespon.deleteAllById(integers);
    }

    public void deleteAll(Iterable<? extends Order> entities) {
        orderRespon.deleteAll(entities);
    }

    public void deleteAll() {
        orderRespon.deleteAll();
    }
}
