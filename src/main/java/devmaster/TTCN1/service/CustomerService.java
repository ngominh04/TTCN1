package devmaster.TTCN1.service;

import devmaster.TTCN1.domain.Customer;
import devmaster.TTCN1.respository.CustomerRespon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    CustomerRespon customerRespon;

    public <S extends Customer> S save(S entity) {
        return customerRespon.save(entity);
    }

    public <S extends Customer> Iterable<S> saveAll(Iterable<S> entities) {
        return customerRespon.saveAll(entities);
    }

    public Optional<Customer> findById(Integer integer) {
        return customerRespon.findById(integer);
    }

    public boolean existsById(Integer integer) {
        return customerRespon.existsById(integer);
    }

    public Iterable<Customer> findAll() {
        return customerRespon.findAll();
    }

    public Iterable<Customer> findAllById(Iterable<Integer> integers) {
        return customerRespon.findAllById(integers);
    }

    public long count() {
        return customerRespon.count();
    }

    public void deleteById(Integer integer) {
        customerRespon.deleteById(integer);
    }

    public void delete(Customer entity) {
        customerRespon.delete(entity);
    }

    public void deleteAllById(Iterable<? extends Integer> integers) {
        customerRespon.deleteAllById(integers);
    }

    public void deleteAll(Iterable<? extends Customer> entities) {
        customerRespon.deleteAll(entities);
    }

    public void deleteAll() {
        customerRespon.deleteAll();
    }
}
