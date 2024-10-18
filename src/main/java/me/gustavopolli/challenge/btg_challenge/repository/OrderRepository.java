package me.gustavopolli.challenge.btg_challenge.repository;

import me.gustavopolli.challenge.btg_challenge.entity.Order;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface OrderRepository extends MongoRepository<Order, Long> {

    List<Order> findAllByCostumerId(Long costumerId, Pageable pageable);
}
