package devmaster.TTCN1.service;

import devmaster.TTCN1.domain.Order;
import devmaster.TTCN1.respository.OrderRespon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    OrderRespon orderRespon;

    public Order save(Order entity) {
        return orderRespon.save(entity);
    }

    public List<Order> saveAll(List<Order> entities) {
        return (List<Order>) orderRespon.saveAll(entities);
    }

    public Optional<Order> findById(Integer integer) {
        return orderRespon.findById(integer);
    }

    public boolean existsById(Integer integer) {
        return orderRespon.existsById(integer);
    }

    public List<Order> findAll() {
        return (List<Order>) orderRespon.findAll();
    }

    public List<Order> findAllById(List<Integer> integers) {
        return (List<Order>) orderRespon.findAllById(integers);
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

    public void deleteAllById(List<Integer> integers) {
        orderRespon.deleteAllById(integers);
    }

    public void deleteAll(List<Order> entities) {
        orderRespon.deleteAll(entities);
    }

    public void deleteAll() {
        orderRespon.deleteAll();
    }
}
