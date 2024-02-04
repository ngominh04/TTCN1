package devmaster.TTCN1.service;

import devmaster.TTCN1.domain.OrdersPayment;
import devmaster.TTCN1.respository.OrderPaymentRespon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderPaymentService {
    @Autowired
    OrderPaymentRespon orderPaymentRespon;

    public <S extends OrdersPayment> S save(S entity) {
        return orderPaymentRespon.save(entity);
    }

    public <S extends OrdersPayment> Iterable<S> saveAll(Iterable<S> entities) {
        return orderPaymentRespon.saveAll(entities);
    }

    public Optional<OrdersPayment> findById(Integer integer) {
        return orderPaymentRespon.findById(integer);
    }

    public boolean existsById(Integer integer) {
        return orderPaymentRespon.existsById(integer);
    }

    public Iterable<OrdersPayment> findAll() {
        return orderPaymentRespon.findAll();
    }

    public Iterable<OrdersPayment> findAllById(Iterable<Integer> integers) {
        return orderPaymentRespon.findAllById(integers);
    }

    public long count() {
        return orderPaymentRespon.count();
    }

    public void deleteById(Integer integer) {
        orderPaymentRespon.deleteById(integer);
    }

    public void delete(OrdersPayment entity) {
        orderPaymentRespon.delete(entity);
    }

    public void deleteAllById(Iterable<? extends Integer> integers) {
        orderPaymentRespon.deleteAllById(integers);
    }

    public void deleteAll(Iterable<? extends OrdersPayment> entities) {
        orderPaymentRespon.deleteAll(entities);
    }

    public void deleteAll() {
        orderPaymentRespon.deleteAll();
    }
}
