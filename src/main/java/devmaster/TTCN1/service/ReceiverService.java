package devmaster.TTCN1.service;

import devmaster.TTCN1.domain.Receiver;
import devmaster.TTCN1.respository.ReceiverRespon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReceiverService {
    @Autowired
    ReceiverRespon receiverRespon;

    public Receiver save(Receiver entity) {
        return receiverRespon.save(entity);
    }

    public List<Receiver> saveAll(List<Receiver> entities) {
        return (List<Receiver>) receiverRespon.saveAll(entities);
    }

    public Optional<Receiver> findById(Integer integer) {
        return receiverRespon.findById(integer);
    }

    public boolean existsById(Integer integer) {
        return receiverRespon.existsById(integer);
    }

    public List<Receiver> findAll() {
        return (List<Receiver>) receiverRespon.findAll();
    }

    public List<Receiver> findAllById(List<Integer> integers) {
        return (List<Receiver>) receiverRespon.findAllById(integers);
    }

    public long count() {
        return receiverRespon.count();
    }

    public void deleteById(Integer integer) {
        receiverRespon.deleteById(integer);
    }

    public void delete(Receiver entity) {
        receiverRespon.delete(entity);
    }

    public void deleteAllById(List<Integer> integers) {
        receiverRespon.deleteAllById(integers);
    }

    public void deleteAll(List<Receiver> entities) {
        receiverRespon.deleteAll(entities);
    }

    public void deleteAll() {
        receiverRespon.deleteAll();
    }
}
