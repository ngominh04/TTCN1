package devmaster.TTCN1.service;

import devmaster.TTCN1.domain.OrdersDetail;
import devmaster.TTCN1.respository.OrderDetailRespon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderDetailService {
    @Autowired
    OrderDetailRespon orderDetailRespon;

    public <S extends OrdersDetail> S save(S entity) {
        return orderDetailRespon.save(entity);
    }

    public <S extends OrdersDetail> Iterable<S> saveAll(Iterable<S> entities) {
        return orderDetailRespon.saveAll(entities);
    }

    public Optional<OrdersDetail> findById(Integer integer) {
        return orderDetailRespon.findById(integer);
    }

    public boolean existsById(Integer integer) {
        return orderDetailRespon.existsById(integer);
    }

    public Iterable<OrdersDetail> findAll() {
        return orderDetailRespon.findAll();
    }

    public Iterable<OrdersDetail> findAllById(Iterable<Integer> integers) {
        return orderDetailRespon.findAllById(integers);
    }

    public long count() {
        return orderDetailRespon.count();
    }

    public void deleteById(Integer integer) {
        orderDetailRespon.deleteById(integer);
    }

    public void delete(OrdersDetail entity) {
        orderDetailRespon.delete(entity);
    }

    public void deleteAllById(Iterable<? extends Integer> integers) {
        orderDetailRespon.deleteAllById(integers);
    }

    public void deleteAll(Iterable<? extends OrdersDetail> entities) {
        orderDetailRespon.deleteAll(entities);
    }

    public void deleteAll() {
        orderDetailRespon.deleteAll();
    }
}
