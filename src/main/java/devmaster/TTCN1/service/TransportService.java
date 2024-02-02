package devmaster.TTCN1.service;

import devmaster.TTCN1.domain.TransportMethod;
import devmaster.TTCN1.respository.TransportRespon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransportService {
    @Autowired
    TransportRespon transportRespon;

    public TransportMethod save(TransportMethod entity) {
        return transportRespon.save(entity);
    }

    public List<TransportMethod> saveAll(List<TransportMethod> entities) {
        return (List<TransportMethod>) transportRespon.saveAll(entities);
    }

    public Optional<TransportMethod> findById(Integer integer) {
        return transportRespon.findById(integer);
    }

    public boolean existsById(Integer integer) {
        return transportRespon.existsById(integer);
    }

    public List<TransportMethod> findAll() {
        return (List<TransportMethod>) transportRespon.findAll();
    }

    public List<TransportMethod> findAllById(List<Integer> integers) {
        return (List<TransportMethod>) transportRespon.findAllById(integers);
    }

    public long count() {
        return transportRespon.count();
    }

    public void deleteById(Integer integer) {
        transportRespon.deleteById(integer);
    }

    public void delete(TransportMethod entity) {
        transportRespon.delete(entity);
    }

    public void deleteAllById(List<Integer> integers) {
        transportRespon.deleteAllById(integers);
    }

    public void deleteAll(List<TransportMethod> entities) {
        transportRespon.deleteAll(entities);
    }

    public void deleteAll() {
        transportRespon.deleteAll();
    }
}
