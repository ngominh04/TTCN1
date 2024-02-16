package devmaster.TTCN1.service;

import devmaster.TTCN1.domain.Evaluate;
import devmaster.TTCN1.respository.EvaluateRespon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EvaluateService {
    @Autowired
    EvaluateRespon evaluateRespon;

    public <S extends Evaluate> S save(S entity) {
        return evaluateRespon.save(entity);
    }

    public <S extends Evaluate> Iterable<S> saveAll(Iterable<S> entities) {
        return evaluateRespon.saveAll(entities);
    }

    public Optional<Evaluate> findById(Integer integer) {
        return evaluateRespon.findById(integer);
    }

    public boolean existsById(Integer integer) {
        return evaluateRespon.existsById(integer);
    }

    public Iterable<Evaluate> findAll() {
        return evaluateRespon.findAll();
    }

    public Iterable<Evaluate> findAllById(Iterable<Integer> integers) {
        return evaluateRespon.findAllById(integers);
    }

    public long count() {
        return evaluateRespon.count();
    }

    public void deleteById(Integer integer) {
        evaluateRespon.deleteById(integer);
    }

    public void delete(Evaluate entity) {
        evaluateRespon.delete(entity);
    }

    public void deleteAllById(Iterable<? extends Integer> integers) {
        evaluateRespon.deleteAllById(integers);
    }

    public void deleteAll(Iterable<? extends Evaluate> entities) {
        evaluateRespon.deleteAll(entities);
    }

    public void deleteAll() {
        evaluateRespon.deleteAll();
    }
}
