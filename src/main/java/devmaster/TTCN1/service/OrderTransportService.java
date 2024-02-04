package devmaster.TTCN1.service;

import devmaster.TTCN1.domain.OrdersTransport;
import devmaster.TTCN1.respository.OrderTransportRespon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderTransportService {
    @Autowired
    OrderTransportRespon orderTransportRespon;

    public <S extends OrdersTransport> S save(S entity) {
        return orderTransportRespon.save(entity);
    }

    public <S extends OrdersTransport> Iterable<S> saveAll(Iterable<S> entities) {
        return orderTransportRespon.saveAll(entities);
    }

    public Optional<OrdersTransport> findById(Integer integer) {
        return orderTransportRespon.findById(integer);
    }

    public boolean existsById(Integer integer) {
        return orderTransportRespon.existsById(integer);
    }

    public Iterable<OrdersTransport> findAll() {
        return orderTransportRespon.findAll();
    }

    public Iterable<OrdersTransport> findAllById(Iterable<Integer> integers) {
        return orderTransportRespon.findAllById(integers);
    }

    public long count() {
        return orderTransportRespon.count();
    }

    public void deleteById(Integer integer) {
        orderTransportRespon.deleteById(integer);
    }

    public void delete(OrdersTransport entity) {
        orderTransportRespon.delete(entity);
    }

    public void deleteAllById(Iterable<? extends Integer> integers) {
        orderTransportRespon.deleteAllById(integers);
    }

    public void deleteAll(Iterable<? extends OrdersTransport> entities) {
        orderTransportRespon.deleteAll(entities);
    }

    public void deleteAll() {
        orderTransportRespon.deleteAll();
    }
}
